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

import com.auth0.jwt.JWTVerifier
import play.api.libs.json.{ JsObject, Json }

case class AccessGrant( accessToken: String ) {

  def verifyAccessToken( verifier: JWTVerifier ): AccessGrantToken = {
    val jwt = verifier.verify( accessToken )

    val resourceIdClaim = jwt.getClaim( "resource_id" )
    val resourceId = if ( resourceIdClaim.isNull ) None else Some( Long.unbox( resourceIdClaim.asLong() ) )

    val scopeArray = jwt.getClaim( "resource_scope" ).asArray( classOf[String] )
    val scope = scopeArray.map( ScopeQualifier.apply ).toSet

    val extraClaim = jwt.getClaim( "resource_extras" )
    val extraAsString = if ( extraClaim.isNull ) None else Some( extraClaim.asString() )
    val extra = extraAsString.map( Json.parse ).map( _.as[JsObject] )

    AccessGrantToken( resourceId, scope, extra, jwt )
  }

}
