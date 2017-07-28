package ch.datascience.service.models.storage

import ch.datascience.service.models.resource.{ AccessRequest, ScopeQualifier, SingleScopeAccessRequest }
import play.api.libs.json.JsObject

/**
 * Created by johann on 13/07/17.
 */
case class CreateFileRequest(
    bucketId: AccessRequest#PermissionHolderId,
    fileName: String
) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest {

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
    SingleScopeAccessRequest( permissionHolderId = None, CreateFileRequest.scope, extraClaims )
  }

}

object CreateFileRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.StorageCreate
}
