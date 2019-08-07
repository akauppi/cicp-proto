package backend

import java.util.concurrent.CompletableFuture

import akka.http.scaladsl.server.directives.{AuthenticationDirective, Credentials}
import akka.http.scaladsl.server.Directives._
import com.google.firebase.auth.{FirebaseAuth, FirebaseToken}

import scala.concurrent.Future
import scala.compat.java8.FutureConverters

/*
* Validating Firebase tokens is done using its Admin Auth API.
*
* References:
*   - "Introduction to the Admin Auth API" (Firebase docs)
*     https://firebase.google.com/docs/auth/admin/
*
*     "You can also use the service to identify these users on your own server. This lets you securely perform
*     server-side logic on behalf of users that have signed in with Firebase Authentication."
*/
object FirebaseAuthenticator {
  import scala.concurrent.ExecutionContext.Implicits.global

  // The directive used in routes. Named according to other Akka HTTP directives (e.g. 'authenticateBasic[Async]').
  //
  def authenticateFirebaseAsync(realm: String): AuthenticationDirective[FirebaseToken] = {

    authenticateOAuth2Async(realm, {
      case Credentials.Missing => Future.successful(None)
      case Credentials.Provided(token) =>
        fba.verifyIdTokenAsync(token).asScala
          .map( Some(_) )
    } )
  }

  private
  val fba: FirebaseAuth = FirebaseAuth.getInstance()

  // Scala 2.13: this no longer needed with it, use '.asScala'    #scala213
  //  -> https://stackoverflow.com/questions/35033290/convert-a-java-future-to-a-scala-future
  //
  private
  implicit class JavaFutureRich[T](jf: java.util.concurrent.Future[T]) {
    def asScala: Future[T] = {
      FutureConverters.toScala( CompletableFuture.supplyAsync( () => jf.get ) )
    }
  }
}
