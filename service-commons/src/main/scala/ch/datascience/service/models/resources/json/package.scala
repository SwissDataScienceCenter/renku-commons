package ch.datascience.service.models.resources

import play.api.libs.json._

/**
  * Created by johann on 25/04/17.
  */
package object json {

  implicit lazy val readResourceRequestFormat: Format[ReadResourceRequest] = Format(ReadResourceRequestMappers.readResourceRequestReads, ReadResourceRequestMappers.readResourceRequestWrites)
  implicit lazy val writeResourceRequestFormat: Format[WriteResourceRequest] = Format(WriteResourceRequestMappers.writeResourceRequestReads, WriteResourceRequestMappers.writeResourceRequestWrites)
  implicit lazy val createBucketRequestFormat: Format[CreateBucketRequest] = Format(CreateBucketRequestMappers.createBucketRequestReads, CreateBucketRequestMappers.createBucketRequestWrites)
}
