package ch.datascience.service.utils.persistence.scope

import javax.inject.{ Inject, Singleton }

import ch.datascience.graph.scope.persistence.remote.StandardRemotePersistenceLayer
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
 * Created by johann on 13/06/17.
 */
@Singleton
class RemotePersistenceLayer @Inject() (
    override val client: ScopeClient
) extends StandardRemotePersistenceLayer( client = client )
