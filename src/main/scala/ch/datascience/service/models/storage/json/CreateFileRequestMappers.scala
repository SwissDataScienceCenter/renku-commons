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

package ch.datascience.service.models.storage.json

import ch.datascience.graph.Constants.VertexId
import ch.datascience.service.models.resource.json.RequestTypeMappers
import ch.datascience.service.models.storage.CreateFileRequest
import play.api.libs.functional.syntax._
import play.api.libs.json._

/**
 * Created by johann on 13/07/17.
 */
private[json] object CreateFileRequestMappers {

  def CreateFileRequestFormat: OFormat[CreateFileRequest] = RequestTypeMappers.format( "create_file" )( (
    ( JsPath \ "bucket_id" ).format[VertexId] and
    ( JsPath \ "file_name" ).format[String] and
    ( JsPath \ "labels" ).formatNullable[Seq[String]]
  )( read, write ) )

  private[this] def read(
      bucketId: VertexId,
      fileName: String,
      labels:   Option[Seq[String]]
  ): CreateFileRequest = {
    CreateFileRequest(
      bucketId,
      fileName,
      labels.map( _.toSet ).getOrElse( Set.empty )
    )
  }

  private[this] def write( request: CreateFileRequest ): ( VertexId, String, Option[Seq[String]] ) = {
    val labels = if ( request.labels.isEmpty ) None else Some( request.labels.toSeq )
    ( request.bucketId, request.fileName, labels )
  }

}
