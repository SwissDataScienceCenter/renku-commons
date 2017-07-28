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

import ch.datascience.service.models.resource.ScopeQualifier
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.util.Try

/**
 * Created by johann on 13/07/17.
 */
object ScopeQualifierMappers {

  def ScopeQualifierFormat: Format[ScopeQualifier] = Format( ScopeQualifierReads, ScopeQualifierWrites )

  def ScopeQualifierReads: Reads[ScopeQualifier] = Reads { json =>
    json.validate[String].flatMap { str =>
      Try { ScopeQualifier( str ) }.map( s => JsSuccess( s ) ).recover { case e => JsError( e.getMessage ) }.get
    }
  }

  def ScopeQualifierWrites: Writes[ScopeQualifier] = implicitly[Writes[String]].contramap( _.name )

}
