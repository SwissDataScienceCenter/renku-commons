package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
 * Created by johann on 14/07/17.
 */
case class AccessRequest(
    permissionHolderId: Option[AccessRequest#PermissionHolderId],
    scope:              Set[ScopeQualifier],
    extraClaims:        Option[JsObject]
) {

  type PermissionHolderId = Long

}

object AccessRequest {

  trait ToAccessRequest {
    def toAccessRequest( extraClaims: Option[JsObject] ): AccessRequest
  }

}
