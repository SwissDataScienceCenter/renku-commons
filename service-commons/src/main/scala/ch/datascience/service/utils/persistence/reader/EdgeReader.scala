package ch.datascience.service.utils.persistence.reader

import javax.inject.Inject

import ch.datascience.graph.elements.tinkerpop_mappers.{EdgeReader => Base}
import ch.datascience.service.utils.persistence.scope.Scope

/**
  * Created by johann on 13/06/17.
  */
class EdgeReader @Inject()(override val scope: Scope) extends Base(scope = scope)
