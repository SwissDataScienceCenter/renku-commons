//package ch.datascience.typesystem.graphdb
//
//import org.janusgraph.core.JanusGraph
//import org.janusgraph.core.schema.JanusGraphManagement
//
//import scala.concurrent.{ExecutionContext, Future}
//
///**
//  * Created by johann on 21/03/17.
//  */
//trait JanusGraphManager {
//
//  val graph: JanusGraph
//
//  protected val ec: ExecutionContext
//
//  def run[A](f: (JanusGraphManagement) => A): Future[A] = run(GraphManagementAction(f))
//
//  def run[A](f: GraphManagementAction[A]): Future[A] = Future {
//    val mgmt = graph.openManagement()
//    try {
//      val result: A = f(mgmt)
//      mgmt.commit()
//      result
//    } catch {
//      case e: Exception =>
//        mgmt.rollback()
//        throw e
//    }
//  } (ec)
//
//}
