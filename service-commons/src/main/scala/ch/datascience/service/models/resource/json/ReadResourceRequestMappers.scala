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

import ch.datascience.service.models.resource.{ReadResourceRequest, ScopeQualifier}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._


object ReadResourceRequestMappers {

  def ReadResourceRequestFormat: OFormat[ReadResourceRequest] = (
    (JsPath \ "scope").format[ScopeQualifier](Reads.filter[ScopeQualifier](ValidationError("wrong scope"))(_ == ReadResourceRequest.scope)) and
      (JsPath \ "resource_id").format[Long] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
    )(read, write)

  private[this] def read(
    scope: ScopeQualifier,
    resourceId: Long,
    extraClaims: Option[JsObject]
  ): ReadResourceRequest = ReadResourceRequest(resourceId, extraClaims)

  private[this] def write(req: ReadResourceRequest): (ScopeQualifier, Long, Option[JsObject]) = {
    (req.scope, req.resourceId, req.extraClaims)
  }

}
