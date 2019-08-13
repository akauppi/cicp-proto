package backend

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.{ActorMaterializer, Materializer}
import backend.tools.AnyRoute
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable
import com.google.firebase.FirebaseApp

import akka.http.scaladsl.server.Directives._

/*
*/
object Main extends App with LazyLogging {
  import Conf.port
  private val interface = "0.0.0.0"   // all interfaces

  implicit val as: ActorSystem = ActorSystem("backend")
  implicit val mat: Materializer = ActorMaterializer()
  import as.dispatcher    // ExecutionContext

  val mmap: mutable.Map[String,UserInfo] = mutable.Map.empty    // tbd. Maybe Scala 2.13 has some concurrent set?

  val route: Route =
    new OthersRoute{
      override def addMe(o: UserInfo) = { mmap += o.uid -> o }
      override def gimmeAll(): Set[UserInfo] = mmap.values.toSet
    } ~
    new ForgetRoute{
      override def forgetMe(o: UserInfo) = mmap.remove(o.uid)
    } ~ (get & path("ping")) {    // for health check
      complete("pong")
    }

  val app: FirebaseApp = FirebaseApp.initializeApp()  // steer with 'FIREBASE_CONFIG' env.var if needed;
                                                      // see https://firebase.google.com/docs/admin/setup/#initialize_without_parameters

  logger.info(s"Access to Firebase project '${app.getName}' initialized")

  Http().bindAndHandle( route, interface, port )

  logger.info(s"Running at http://$interface:$port")
}
