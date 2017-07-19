package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.ScopeQualifier
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.Try

/**
  * Created by johann on 13/07/17.
  */
object ScopeQualifierMappers {

  def ScopeQualifierFormat: Format[ScopeQualifier] = Format(ScopeQualifierReads, ScopeQualifierWrites)

  def ScopeQualifierReads: Reads[ScopeQualifier] = Reads { json =>
    json.validate[String].flatMap { str =>
      Try{ ScopeQualifier(str) }.map(s => JsSuccess(s)).recover { case e => JsError(e.getMessage) }.get
    }
  }

  def ScopeQualifierWrites: Writes[ScopeQualifier] = implicitly[Writes[String]].contramap(_.name)

}
