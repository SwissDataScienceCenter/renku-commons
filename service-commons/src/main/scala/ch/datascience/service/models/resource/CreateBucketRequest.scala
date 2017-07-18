package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by jeberle on 09.06.17.
  */
case class CreateBucketRequest(
  name: String,
  backend: String,
  backendOptions: Option[JsObject],
  override val extraClaims: Option[JsObject]
) extends SingleScopeAccessRequest(permissionHolderId = None, scope = CreateBucketRequest.scope, extraClaims = extraClaims)

object CreateBucketRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.BucketCreate
}
