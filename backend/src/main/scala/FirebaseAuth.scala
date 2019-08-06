package backend

import akka.http.scaladsl.server.directives.{AuthenticationDirective, Credentials}
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Future

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
object FirebaseAuth {

  case class UserInfo(displayName: String, email: String)

  val firebaseAuth: AuthenticationDirective[UserInfo] = {
    authenticateOAuth2Async(realm, myAuthenticator)
  }

  private
  val realm: String = "public"    // tbd. what should we have?

  private
  def myAuthenticator(creds: Credentials): Future[Option[UserInfo]] = {

    val decodedToken: String = FirebaseAuth.getInstance().verifyIdToken(idToken)
    decodedToken
  }
}
