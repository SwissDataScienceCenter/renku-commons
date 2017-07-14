package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{AccessRequest, ResourceScope}
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 14/07/17.
  */
object AccessRequestMappers {

  def AccessRequestFormat: OFormat[AccessRequest] = (
    (JsPath \ "permission_holder_id").formatNullable[AccessRequest#PermissionHolderId] and
      (JsPath \ "scopes").format[Seq[ResourceScope]]
  )({ (i, s) => AccessRequest(i, s: _*) }, { req => (req.permissionHolderId, req.scopes.toSeq) })

}
