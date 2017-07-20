package ch.datascience.service.models.resource

import com.auth0.jwt.interfaces.DecodedJWT
import play.api.libs.json.JsObject

case class AccessGrantToken(
  permissionHolderId: Option[AccessRequest#PermissionHolderId],
  scope: Set[ScopeQualifier],
  extraClaims: Option[JsObject],
  underlying: DecodedJWT
)
