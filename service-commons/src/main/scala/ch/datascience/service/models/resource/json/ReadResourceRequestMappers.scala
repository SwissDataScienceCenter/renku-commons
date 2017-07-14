package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ReadResourceRequest, ResourceScope}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def ReadResourceRequestFormat: OFormat[ReadResourceRequest] = (
    (JsPath \ "scope").format[ResourceScope](Reads.filter[ResourceScope](ValidationError("wring scope"))(_ == ReadResourceRequest.scope)) and
      (JsPath \ "resource_id").format[Long]
    )(read, write)

  private[this] def read(
    scope: ResourceScope,
    resourceId: Long
  ): ReadResourceRequest = ReadResourceRequest(resourceId)

  private[this] def write(req: ReadResourceRequest): (ResourceScope, Long) = {
    (req.scope, req.resourceId)
  }

}
