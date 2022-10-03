package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{Format, Json, OFormat, Writes}
import play.api.mvc._

case class Greet(name: String)

object Greet {
  implicit val greetFormat: OFormat[Greet] = Json.format[Greet]

}

class HelloController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def greet(name: String) = Action { implicit request: Request[AnyContent] =>
    val greet = Greet(name)
    Ok(Json.toJson(greet))
  }
}