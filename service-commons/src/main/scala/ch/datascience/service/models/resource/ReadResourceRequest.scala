package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by jeberle on 09.06.17.
  */
case class ReadResourceRequest(
  override val resourceId: AccessRequest#PermissionHolderId,
  override val extraClaims: Option[JsObject]
) extends SingleScopeResourceAccessRequest(resourceId, scope = ReadResourceRequest.scope, extraClaims = extraClaims)

object ReadResourceRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.StorageRead
}
