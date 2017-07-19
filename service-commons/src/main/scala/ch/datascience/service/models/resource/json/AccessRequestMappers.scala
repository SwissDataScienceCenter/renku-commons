/*
 * Copyright 2017 - Swiss Data Science Center (SDSC)
 * A partnership between École Polytechnique Fédérale de Lausanne (EPFL) and
 * Eidgenössische Technische Hochschule Zürich (ETHZ).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.datascience.service.models.resource.json

import ch.datascience.service.models.resource.{AccessRequest, ScopeQualifier}
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 14/07/17.
  */
object AccessRequestMappers {

  def AccessRequestFormat: OFormat[AccessRequest] = (
    (JsPath \ "permission_holder_id").formatNullable[AccessRequest#PermissionHolderId] and
      (JsPath \ "scope").format[Seq[ScopeQualifier]] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
  )({ (i, s, e) => AccessRequest(i, s.toSet, e) }, { req => (req.permissionHolderId, req.scope.toSeq, req.extraClaims) })

}
