package controllers
import akka.actor.ActorSystem
import akka.stream.Materializer
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.core.server.ServerEndpoint
import sttp.tapir._
import sttp.tapir.server.ServerEndpoint.Full
import sttp.tapir.server.play.PlayServerInterpreter
import sttp.tapir.swagger.bundle.SwaggerInterpreter

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApiRouter @Inject() () extends SimpleRouter {

  implicit val materizer = Materializer(ActorSystem("tests"))
  override def routes: Routes = PlayServerInterpreter().toRoutes(tapirGeneratedRoutes)

  val countCharacters: String => Future[Either[String, String]] = s =>
    Future(Right[String, String](s))

  val greetEndpoint =
    endpoint.get
      .in("greet")
      .in("name" / path[String])
      .errorOut(stringBody)
      .out(stringBody)
      .serverLogic(countCharacters)

  def tapirGeneratedRoutes = {
    val endpoints = List(greetEndpoint)
    endpoints ++ SwaggerInterpreter().fromServerEndpoints(endpoints, "Greeting", "1.0")
  }

}
