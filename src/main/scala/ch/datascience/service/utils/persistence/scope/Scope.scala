package ch.datascience.service.utils.persistence.scope

import javax.inject.{ Inject, Singleton }

import ch.datascience.graph.scope.{ Scope => Base }

/**
 * Created by johann on 13/06/17.
 */
@Singleton
class Scope @Inject() ( override protected val persistenceLayer: RemotePersistenceLayer ) extends Base( persistenceLayer = persistenceLayer )
