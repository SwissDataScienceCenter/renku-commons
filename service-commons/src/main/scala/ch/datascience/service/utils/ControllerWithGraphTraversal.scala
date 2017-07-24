package ch.datascience.service.utils

import ch.datascience.graph.execution.GraphExecutionContext
import ch.datascience.service.utils.persistence.graph.{GraphExecutionContextProvider, JanusGraphTraversalSourceProvider}
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import play.api.mvc.Controller

/**
  * Created by johann on 13/06/17.
  */

trait ControllerWithGraphTraversal { this: Controller =>

  protected def graphExecutionContextProvider: GraphExecutionContextProvider

  implicit protected def graphExecutionContext: GraphExecutionContext = graphExecutionContextProvider.get

  protected def janusGraphTraversalSourceProvider: JanusGraphTraversalSourceProvider

  implicit protected def graphTraversalSource: GraphTraversalSource = janusGraphTraversalSourceProvider.get

}
