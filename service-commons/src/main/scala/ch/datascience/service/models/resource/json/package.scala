package ch.datascience.service.models.resource

import play.api.libs.json.{Format, OFormat}

/**
  * Created by johann on 25/04/17.
  */
package object json {

  implicit lazy val AccessRequestFormat: OFormat[AccessRequest] = AccessRequestMappers.AccessRequestFormat
  implicit lazy val CreateBucketRequestFormat: OFormat[CreateBucketRequest] = CreateBucketRequestMappers.CreateBucketRequestFormat
  implicit lazy val CreateFileRequestFormat: OFormat[CreateFileRequest] = CreateFileRequestMappers.CreateFileRequestFormat
  implicit lazy val ReadResourceRequestFormat: OFormat[ReadResourceRequest] = ReadResourceRequestMappers.ReadResourceRequestFormat
  implicit lazy val ResourceAccessRequestFormat: OFormat[ResourceAccessRequest] = ResourceAccessRequestMappers.ResourceAccessRequestFormat
  implicit lazy val WriteResourceRequestFormat: OFormat[WriteResourceRequest] = WriteResourceRequestMappers.WriteResourceRequestFormat

  implicit lazy val ResourceScopeFormat: Format[ResourceScope] = ResourceScopeMappers.ResourceScopeFormat

}
