package backend

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

object SomethingRoute {
  val route: Route = {

    (get & pathSingleSlash) {
      complete("OK!")
    }
  }
}