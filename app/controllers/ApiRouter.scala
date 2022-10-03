package controllers
import play.api.Play.materializer
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import sttp.tapir._
import sttp.tapir.server.play.PlayServerInterpreter

import javax.inject.Inject

class ApiRouter @Inject() () extends SimpleRouter {

  override def routes: Routes = tapirGeneratedRoutes

  val greetEndpoint =
    endpoint.get
      .in("api")
      .in("name" / path[String])
      .errorOut(stringBody)
      .out(stringBody)

  def tapirGeneratedRoutes:Routes = {
    val greetEndpoint = ???
//      PlayServerInterpreter().toRoutes(greetEndpoint)
  }

}
