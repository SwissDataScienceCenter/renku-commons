package ch.datascience.service.utils.persistence.scope

import javax.inject.Singleton

import ch.datascience.graph.scope.persistence.dummy.{ DummyPersistenceLayer => Base }

@Singleton
class DummyPersistenceLayer() extends Base
