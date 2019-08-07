package backend

import akka.http.scaladsl.server.Directives._

import FirebaseAuthenticator._
import tools.AnyRoute

trait ForgetRoute extends AnyRoute {
  import ForgetRoute._

  protected def forgetMe(o: UserInfo): Unit

  override
  val route = (put & path("forget")) {
    authenticateFirebaseAsync(realm) { fbt =>
      val userInfo = UserInfo(fbt)
      forgetMe(userInfo)
      complete(200, s"removed: ${userInfo.displayName}")    // text doesn't matter
    }
  }
}

object ForgetRoute {
  private
  val realm: String = "public"    // tbd. what should be here?
}