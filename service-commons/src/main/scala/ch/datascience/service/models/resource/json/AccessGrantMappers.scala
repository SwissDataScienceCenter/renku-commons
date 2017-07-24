package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.AccessGrant
import play.api.libs.json._
import play.api.libs.functional.syntax._

object AccessGrantMappers {

  def AccessGrantFormat: OFormat[AccessGrant] = {
    (JsPath \ "access_token").format[String].inmap(AccessGrant, unlift(AccessGrant.unapply))
  }

}
