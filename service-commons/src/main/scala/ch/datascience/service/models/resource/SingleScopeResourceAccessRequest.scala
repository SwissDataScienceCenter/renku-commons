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
class SingleScopeResourceAccessRequest(
    resourceId:  AccessRequest#PermissionHolderId,
    scope:       ScopeQualifier,
    extraClaims: Option[JsObject]
) extends ResourceAccessRequest( resourceId, Set( scope ), extraClaims )

object SingleScopeResourceAccessRequest {

  def apply(
      resourceId:  AccessRequest#PermissionHolderId,
      scope:       ScopeQualifier,
      extraClaims: Option[JsObject]
  ): SingleScopeResourceAccessRequest = new SingleScopeResourceAccessRequest( resourceId, scope, extraClaims )

  trait ToSingleScopeResourceAccessRequest extends ResourceAccessRequest.ToResourceAccessRequest {
    def toAccessRequest( extraClaims: Option[JsObject] ): SingleScopeResourceAccessRequest
  }

}
