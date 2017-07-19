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

import play.api.data.validation.ValidationError
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 18/07/17.
  */
object RequestTypeMappers {

  def format[A](requestType: String)(format: OFormat[A]): OFormat[A] = OFormat(readFilter(requestType)(format), writeWithType(requestType)(format))

  def readFilter[A](requestType: String)(reads: Reads[A]): Reads[A] = {
    val r1 = (JsPath \ "request_type").read[String].filter(ValidationError("wrong type"))(_ == requestType)
    r1.flatMap{ _ => reads }
  }

  def writeWithType[A](requestType: String)(writes: OWrites[A]): OWrites[A] = {
    val t: OWrites[JsObject] = (
      (JsPath \ "request_type").write[String] and
        JsPath.write[JsObject]
      ) { x => (requestType, x) }
    writes.transform(t)
  }

}
