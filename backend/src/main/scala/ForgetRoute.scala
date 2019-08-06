package backend

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import FirebaseAuth._

object ForgetRoute {
  val route: Route = {

    (put & path("forget")) {
      firebaseAuth { userId =>

        ???
      }
    }
  }
}