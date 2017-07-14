package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by jeberle on 09.06.17.
  */
case class CreateBucketRequest(name: String, backend: String, backendOptions: Option[JsObject])
  extends SingleScopeAccessRequest(permissionHolderId = None, scope = CreateBucketRequest.scope)

object CreateBucketRequest {
  lazy val scope: ResourceScope = ResourceScope.BucketCreate
}
