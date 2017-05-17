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

package ch.datascience.graph.elements.persistence.impl

import ch.datascience.graph.elements.{BoxedValue, MultiProperties}
import ch.datascience.graph.elements.persistence.PersistedEdge

/**
  * Created by jeberle on 15.05.17.
  */
case class ImplPersistedEdge[
  +Id,
  Key,
  +Value,
  +PropId
](
  id: Id,
  from: Id,
  to: Id,
  properties: MultiProperties[Key, Value, ImplPersistedMultiRecordProperty[PropId, Key, Value]]
) extends PersistedEdge[Id, Key, Value, ImplPersistedMultiRecordProperty[PropId, Key, Value], Id]
