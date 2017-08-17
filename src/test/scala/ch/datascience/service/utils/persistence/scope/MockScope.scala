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

package ch.datascience.service.utils.persistence.scope

import javax.inject.{ Inject, Singleton }

import ch.datascience.graph.init.InitApplication.readResource
import ch.datascience.graph.init.{ TypeInit, TypeInitFormat }
import ch.datascience.graph.scope.persistence.dummy.DummyScope
import play.api.libs.json.{ JsValue, Json }

/**
 * Created by johann on 13/06/17.
 */
@Singleton
class MockScope @Inject() ( override protected val persistenceLayer: DummyPersistenceLayer )
  extends DummyScope( persistenceLayer = persistenceLayer ) {

  def init(): Unit = {
    val typeInitJson: JsValue = Json.parse( readResource( "/type_init.json" ) )
    val typeInit = typeInitJson.as[TypeInit]( TypeInitFormat )

    val propertyKeyDefinitions = for {
      propertyKey <- typeInit.propertyKeys
    } yield propertyKey.key -> propertyKey

    this.addPropertyDefinitions( propertyKeyDefinitions )

    val namedTypeDefinitions = for {
      namedType <- typeInit.namedTypes
    } yield namedType.typeId -> namedType

    this.addNamedTypeDefinitions( namedTypeDefinitions )
  }

}
