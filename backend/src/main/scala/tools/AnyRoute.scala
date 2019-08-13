package backend.tools

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

/*
* A mechanism  to allow 'ARoute ~ BRoute' definitions. We weren't able to derive from 'Route'.
*/
abstract class AnyRoute {
  /*protected*/ val route: Route

  def ~(tail: AnyRoute): Route = route ~ tail.route
}
