package ch.datascience.service.models.storage

import play.api.libs.json.OFormat

/**
  * Created by johann on 18/07/17.
  */
package object json {

    implicit lazy val CreateBucketRequestFormat: OFormat[CreateBucketRequest] = CreateBucketRequestMappers.CreateBucketRequestFormat
    implicit lazy val CreateFileRequestFormat: OFormat[CreateFileRequest] = CreateFileRequestMappers.CreateFileRequestFormat
    implicit lazy val ReadResourceRequestFormat: OFormat[ReadResourceRequest] = ReadResourceRequestMappers.ReadResourceRequestFormat
    implicit lazy val WriteResourceRequestFormat: OFormat[WriteResourceRequest] = WriteResourceRequestMappers.WriteResourceRequestFormat

}
