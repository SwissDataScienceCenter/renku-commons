package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.ResourceScope
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.Try

/**
  * Created by johann on 13/07/17.
  */
object ResourceScopeMappers {

  def ResourceScopeFormat: Format[ResourceScope] = Format(ResourceScopeReads, ResourceScopeWrites)

  def ResourceScopeReads: Reads[ResourceScope] = Reads { json =>
    json.validate[String].flatMap { str =>
      Try{ ResourceScope(str) }.map(s => JsSuccess(s)).recover { case e => JsError(e.getMessage) }.get
    }
  }

  def ResourceScopeWrites: Writes[ResourceScope] = implicitly[Writes[String]].contramap(_.name)

}
