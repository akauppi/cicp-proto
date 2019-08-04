package backend

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

object TokenRoute {
  val route: Route = {

    (post & path("token")) {

      // Provide a Google CICP token
      ???
    }
  }
}