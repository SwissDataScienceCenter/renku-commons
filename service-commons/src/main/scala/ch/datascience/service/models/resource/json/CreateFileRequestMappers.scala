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

import ch.datascience.service.models.resource.{CreateFileRequest, ScopeQualifier}
import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 13/07/17.
  */
private[json] object CreateFileRequestMappers {

  def CreateFileRequestFormat: OFormat[CreateFileRequest] = (
    (JsPath \ "scope").format[ScopeQualifier](Reads.filter[ScopeQualifier](ValidationError("wring scope"))(_ == CreateFileRequest.scope)) and
      (JsPath \ "bucket_id").format[Long] and
      (JsPath \ "file_name").format[String] and
      (JsPath \ "extra_claims").formatNullable[JsObject]
    )(read, write)

  private[this] def read(
    scope: ScopeQualifier,
    bucketId: Long,
    fileName: String,
    extraClaims: Option[JsObject]
  ): CreateFileRequest = CreateFileRequest(bucketId, fileName, extraClaims)

  private[this] def write(req: CreateFileRequest): (ScopeQualifier, Long, String, Option[JsObject]) = {
    (req.scope, req.bucketId, req.fileName, req.extraClaims)
  }

}
