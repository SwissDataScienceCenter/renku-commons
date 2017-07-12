package ch.datascience.service.utils

import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import play.api.mvc._

/**
  * Created by johann on 25/04/17.
  */
trait ControllerWithBodyParseJson { this: Controller =>

  def  bodyParseJson[A](implicit rds: Reads[A]): BodyParser[A] = parse.json.validate(
    _.validate[A](rds).asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

}
