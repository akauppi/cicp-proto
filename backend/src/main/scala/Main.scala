package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.Future
import scala.util.{Failure, Success}

/*
*/
object Main extends App with LazyLogging {
  import Conf.port
  private val interface = "0.0.0.0"   // all interfaces

  implicit val as: ActorSystem = ActorSystem("backend")
  implicit val mat: Materializer = ActorMaterializer()
  import as.dispatcher    // ExecutionContext

  Http().bindAndHandle( TokenRoute.route, interface, port )

  logger.info(s"Running at http://$interface:$port")
}
