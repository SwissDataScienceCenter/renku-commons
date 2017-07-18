package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 14/07/17.
  */
abstract class SingleScopeAccessRequest(
  val permissionHolderId: Option[AccessRequest#PermissionHolderId],
  val scope: ScopeQualifier,
  val extraClaims: Option[JsObject]
) extends AccessRequest.ToAccessRequest {

  def toAccessRequest: AccessRequest = AccessRequest(permissionHolderId, Set(scope), extraClaims)

}
