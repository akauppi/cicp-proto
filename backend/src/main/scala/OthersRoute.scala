package backend

import akka.http.scaladsl.server.Directives._
import FirebaseAuthenticator._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import tools.AnyRoute

trait OthersRoute extends AnyRoute with FailFastCirceSupport {
  import OthersRoute._
  
  protected def addMe(o: UserInfo): Unit
  protected def gimmeAll(): Set[UserInfo]

  override
  val route = (get & path("others")) {
    authenticateFirebaseAsync(realm) { fbt =>
      val userInfo = UserInfo(fbt)
      addMe(userInfo)
      val others: Set[String] = gimmeAll().filterNot(_.uid == userInfo.uid).map(_.displayName)

      complete(others)
    }
  }
}

object OthersRoute {
  private
  val realm: String = "public"    // tbd. what should be here?
}