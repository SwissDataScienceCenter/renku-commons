package ch.datascience.service.models.resources

import play.api.libs.json.{Format, OFormat}

/**
  * Created by johann on 25/04/17.
  */
package object json {

  implicit lazy val CreateBucketRequestFormat: OFormat[CreateBucketRequest] = CreateBucketRequestMappers.CreateBucketRequestFormat
  implicit lazy val CreateResourceRequestFormat: OFormat[CreateResourceRequest] = CreateResourceRequestMappers.CreateResourceRequestFormat
  implicit lazy val ReadResourceRequestFormat: OFormat[ReadResourceRequest] = ReadResourceRequestMappers.ReadResourceRequestFormat
  implicit lazy val WriteResourceRequestFormat: OFormat[WriteResourceRequest] = WriteResourceRequestMappers.WriteResourceRequestFormat
  implicit lazy val ResourceRequestFormat: OFormat[ResourceRequest] = ResourceRequestMappers.ResourceRequestFormat

  implicit lazy val ResourceScopeFormat: Format[ResourceScope] = ResourceScopeMappers.ResourceScopeFormat

}
