package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by jeberle on 09.06.17.
  */
case class WriteResourceRequest(
  override val resourceId: AccessRequest#PermissionHolderId,
  override val extraClaims: Option[JsObject]
) extends SingleScopeResourceAccessRequest(resourceId, scope = WriteResourceRequest.scope, extraClaims = extraClaims)

object WriteResourceRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.StorageWrite
}
