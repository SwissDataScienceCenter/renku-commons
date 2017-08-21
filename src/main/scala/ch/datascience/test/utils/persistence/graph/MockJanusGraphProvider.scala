package ch.datascience.test.utils.persistence.graph

import javax.inject.{ Inject, Singleton }

import ch.datascience.service.utils.persistence.graph.{ JanusGraphConfigProvider, JanusGraphProvider }
import org.janusgraph.core.{ JanusGraph, JanusGraphFactory }
import play.api.inject.ApplicationLifecycle

@Singleton
class MockJanusGraphProvider @Inject() (
    override protected val config:    JanusGraphConfigProvider,
    override protected val lifecycle: ApplicationLifecycle
) extends JanusGraphProvider( config, lifecycle ) {

  override lazy val graph: JanusGraph = JanusGraphFactory.build().set( "storage.backend", "inmemory" ).open()

}
