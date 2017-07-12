package ch.datascience.service.utils

import play.api.libs.json._
import play.api.mvc._

/**
  * Created by johann on 25/04/17.
  */
trait ControllerWithBodyParseJson { this: BaseController =>

  def  bodyParseJson[A](implicit rds: Reads[A]): BodyParser[A] = parse.json.validate(
    _.validate[A](rds).asEither.left.map(e => BadRequest(JsError.toJson(e)))
  )

}
