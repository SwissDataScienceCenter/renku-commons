package ch.datascience.service.models.storage

import ch.datascience.service.models.resource.{ AccessRequest, ScopeQualifier, SingleScopeResourceAccessRequest }
import play.api.libs.json.JsObject

/**
 * Created by jeberle on 09.06.17.
 */
case class ReadResourceRequest(
    resourceId: AccessRequest#PermissionHolderId
) extends SingleScopeResourceAccessRequest.ToSingleScopeResourceAccessRequest {

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeResourceAccessRequest = {
    SingleScopeResourceAccessRequest( resourceId, ReadResourceRequest.scope, extraClaims )
  }

}

object ReadResourceRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.StorageRead
}
