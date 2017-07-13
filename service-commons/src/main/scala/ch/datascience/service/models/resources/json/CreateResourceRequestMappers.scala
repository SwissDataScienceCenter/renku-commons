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

package ch.datascience.service.models.resources.json

import ch.datascience.service.models.resources.{CreateFileRequest, CreateResourceRequest}
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
  * Created by johann on 13/07/17.
  */
object CreateResourceRequestMappers {

  def CreateResourceRequestFormat: OFormat[CreateResourceRequest] = OFormat(CreateResourceRequestReads, CreateResourceRequestWrites)

  def CreateResourceRequestReads: Reads[CreateResourceRequest] = {
    (JsPath \ "resource_type").read[String].flatMap {
      case "file" => JsPath.read[CreateFileRequest].map(req => req: CreateResourceRequest)
    }
  }

  def CreateResourceRequestWrites: OWrites[CreateResourceRequest] = {
    val w1: OWrites[(String, JsObject)] = (
      (JsPath \ "resource_type").write[String] and
      JsPath.write[JsObject]
    )(unlift(Tuple2.unapply[String, JsObject]))

    w1.contramap { req: CreateResourceRequest =>
      req match {
        case r: CreateFileRequest => ("file", CreateFileRequestFormat.writes(r))
      }
    }
  }

  private[this] implicit lazy val CreateFileRequestFormat: OFormat[CreateFileRequest] = CreateFileRequestMappers.CreateFileRequestFormat

}
