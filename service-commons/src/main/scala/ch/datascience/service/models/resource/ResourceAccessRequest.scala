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

package ch.datascience.service.models.resource

import play.api.libs.json.JsObject

/**
  * Created by johann on 14/07/17.
  */
case class ResourceAccessRequest(
  resourceId: AccessRequest#PermissionHolderId,
  scope: Set[ScopeQualifier],
  extraClaims: Option[JsObject]
) extends AccessRequest.ToAccessRequest {

  override def toAccessRequest: AccessRequest = AccessRequest(Some(resourceId), scope, extraClaims)

}

object ResourceAccessRequest {

  trait ToResourceAccessRequest extends AccessRequest.ToAccessRequest {
    def toResourceAccessRequest: ResourceAccessRequest

    final def toAccessRequest: AccessRequest = toResourceAccessRequest.toAccessRequest
  }

}
