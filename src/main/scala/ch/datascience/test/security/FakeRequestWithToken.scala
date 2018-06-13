/*
 * Copyright 2017-2018 - Swiss Data Science Center (SDSC)
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

package ch.datascience.test.security

import ch.datascience.service.security
import com.auth0.jwt.interfaces.DecodedJWT
import play.api.libs.typedmap.TypedMap
import play.api.mvc.Headers
import play.api.test.FakeRequest

object FakeRequestWithToken {

  implicit class RichFakeRequest[A]( underlying: FakeRequest[A] ) {

    def withToken( token: DecodedJWT ): FakeRequest[A] = {
      underlying
        .withAttrs( TypedMap( security.VerifiedBearerToken -> token.getToken ) )
        .withHeaders( Headers( "Authorization" -> s"Bearer ${token.getToken}" ) )
    }

  }

}
