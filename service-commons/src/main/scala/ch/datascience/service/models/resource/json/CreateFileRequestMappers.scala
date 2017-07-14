package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{CreateFileRequest, ResourceScope}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 13/07/17.
  */
private[json] object CreateFileRequestMappers {

  def CreateFileRequestFormat: OFormat[CreateFileRequest] = (
    (JsPath \ "scope").format[ResourceScope](Reads.filter[ResourceScope](ValidationError("wring scope"))(_ == CreateFileRequest.scope)) and
      (JsPath \ "bucket_id").format[Long] and
      (JsPath \ "file_name").format[String]
    )(read, write)

  private[this] def read(
    scope: ResourceScope,
    bucketId: Long,
    fileName: String
  ): CreateFileRequest = CreateFileRequest(bucketId, fileName)

  private[this] def write(req: CreateFileRequest): (ResourceScope, Long, String) = {
    (req.scope, req.bucketId, req.fileName)
  }

}
