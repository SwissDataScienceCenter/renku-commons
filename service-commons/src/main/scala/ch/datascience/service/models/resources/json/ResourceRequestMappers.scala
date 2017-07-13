package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.{ResourceRequest, ResourceScope}
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ResourceRequestMappers {

  def ResourceRequestFormat: OFormat[ResourceRequest] = (
    (JsPath \ "resource_id").format[Long] and
      (JsPath \ "scopes").format[Set[ResourceScope]](scopesFormat)
  )(ResourceRequest.apply, unlift(ResourceRequest.unapply))

  private[this] def scopesFormat: Format[Set[ResourceScope]] = {
    implicitly[Format[Seq[ResourceScope]]].inmap(
      _.toSet,
      _.toSeq
    )
  }

}
