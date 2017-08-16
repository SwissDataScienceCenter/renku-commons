package ch.datascience.service.models.storage

import ch.datascience.service.models.resource.{ ScopeQualifier, SingleScopeAccessRequest }
import play.api.libs.json.JsObject

/**
 * Created by jeberle on 09.06.17.
 */
case class CreateBucketRequest(
    name:           String,
    backend:        String,
    backendOptions: Option[JsObject]
) extends SingleScopeAccessRequest.ToSingleScopeAccessRequest {

  def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeAccessRequest = {
    SingleScopeAccessRequest( permissionHolderId = None, CreateBucketRequest.scope, extraClaims )
  }

}

object CreateBucketRequest {
  lazy val scope: ScopeQualifier = ScopeQualifier.BucketCreate
}
