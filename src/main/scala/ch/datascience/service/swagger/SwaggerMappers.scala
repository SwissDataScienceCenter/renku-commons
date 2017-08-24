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

package ch.datascience.service.swagger

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

object SwaggerMappers {

  def updateHostAndSchemes( host: String, schemes: Seq[String] ): Reads[JsObject] = {
    updateHost( host ) andThen updateSchemes( schemes )
  }

  def updateHost( host: String ): Reads[JsObject] = {
    (
      ( JsPath \ "host" ).json.prune and
      ( JsPath \ "host" ).json.put( JsString( host ) )
    ).reduce
  }

  def updateSchemes( schemes: Seq[String] ): Reads[JsObject] = {
    (
      ( JsPath \ "schemes" ).json.prune and
      ( JsPath \ "schemes" ).json.put( JsArray( schemes.map( JsString ) ) )
    ).reduce
  }

}
