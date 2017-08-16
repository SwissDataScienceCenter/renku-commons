package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{ AccessRequest, ScopeQualifier }
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
 * Created by johann on 14/07/17.
 */
object AccessRequestMappers {

  def AccessRequestFormat: OFormat[AccessRequest] = (
    ( JsPath \ "resource_id" ).formatNullable[AccessRequest#PermissionHolderId] and
    ( JsPath \ "scope" ).format[Seq[ScopeQualifier]] and
    ( JsPath \ "service_claims" ).formatNullable[JsObject]
  )( { ( i, s, e ) => AccessRequest( i, s.toSet, e ) }, { req => ( req.permissionHolderId, req.scope.toSeq, req.extraClaims ) } )

}
