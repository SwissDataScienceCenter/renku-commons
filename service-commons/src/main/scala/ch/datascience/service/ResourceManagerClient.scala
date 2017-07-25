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

package ch.datascience.service

import javax.inject.Inject

import ch.datascience.service.models.resource.AccessGrant
import ch.datascience.service.models.resource.json.AccessGrantFormat
import play.api.Configuration
import play.api.libs.json._
import play.api.libs.ws.{WSClient, WSRequest}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class ResourceManagerClient @Inject()(configuration: Configuration)(implicit context: ExecutionContext, ws: WSClient) {

  lazy val rmConfig: Configuration = configuration.getConfig("resource-manager.service").get

  lazy val host: String = rmConfig.getString("host").get

  def authorize[T](writer: Writes[T], request: T, token: String): Future[Option[AccessGrant]] = {
    val wsRequest: WSRequest = ws.url(host + "/authorize")
      .withHeaders("Accept" -> "application/json", "Authorization" -> token)
      .withRequestTimeout(10000.millis)
    wsRequest.post(Json.toJson(request)(writer)).map {
      response =>
        response.json.validate(AccessGrantFormat).asOpt
    }
  }

}
