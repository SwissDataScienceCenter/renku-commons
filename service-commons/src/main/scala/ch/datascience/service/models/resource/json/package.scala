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

import play.api.libs.json.{Format, OFormat}

/**
  * Created by johann on 25/04/17.
  */
package object json {

  implicit lazy val AccessRequestFormat: OFormat[AccessRequest] = AccessRequestMappers.AccessRequestFormat
  implicit lazy val CreateBucketRequestFormat: OFormat[CreateBucketRequest] = CreateBucketRequestMappers.CreateBucketRequestFormat
  implicit lazy val CreateFileRequestFormat: OFormat[CreateFileRequest] = CreateFileRequestMappers.CreateFileRequestFormat
  implicit lazy val ReadResourceRequestFormat: OFormat[ReadResourceRequest] = ReadResourceRequestMappers.ReadResourceRequestFormat
  implicit lazy val ResourceAccessRequestFormat: OFormat[ResourceAccessRequest] = ResourceAccessRequestMappers.ResourceAccessRequestFormat
  implicit lazy val WriteResourceRequestFormat: OFormat[WriteResourceRequest] = WriteResourceRequestMappers.WriteResourceRequestFormat

  implicit lazy val ResourceScopeFormat: Format[ResourceScope] = ResourceScopeMappers.ResourceScopeFormat

}
