package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 13/07/17.
  */
case class CreateFileRequest(
  bucketId: AccessRequest#PermissionHolderId,
  fileName: String,
  override val extraClaims: Option[JsObject]
) extends SingleScopeAccessRequest(permissionHolderId = Some(bucketId), scope = CreateFileRequest.scope, extraClaims = extraClaims)

object CreateFileRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.StorageCreate
}
