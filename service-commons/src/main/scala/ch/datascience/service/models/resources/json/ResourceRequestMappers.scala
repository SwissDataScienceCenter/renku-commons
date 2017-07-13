package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources._
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ResourceRequestMappers {

  def resourcesRequestReads: Reads[ResourceRequest] = (
    (JsPath \ "app_id").readNullable[Long] and
      (JsPath \ "type").read[String].flatMap {
        case "read:file" => (JsPath \ "details").read[ReadResourceRequest]
        case "write:file" => (JsPath \ "details").read[WriteResourceRequest]
        case "create:bucket" => (JsPath \ "details").read[CreateBucketRequest]
      }
    )(ResourceRequest.apply _)

  def resourcesRequestWrites: Writes[ResourceRequest] = (
    (JsPath \ "app_id").writeNullable[Long] and
      (JsPath \ "type").write[String] and
      (JsPath \ "details").write[ResourceRequestDetails]
    ) { rr => rr.details match {
    case _: ReadResourceRequest => (rr.appId, "read:file", rr.details)
    case _: WriteResourceRequest => (rr.appId, "write:file", rr.details)
    case _: CreateBucketRequest => (rr.appId, "create:bucket", rr.details)
}}
}

