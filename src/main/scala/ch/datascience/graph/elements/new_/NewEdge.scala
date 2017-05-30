package ch.datascience.graph.elements.new_

import ch.datascience.graph.elements.Edge
import ch.datascience.graph.elements.detached.DetachedProperty
import ch.datascience.graph.elements.new_.impl.ImplNewEdge
import ch.datascience.graph.elements.persisted.PersistedVertex

/**
  * Created by johann on 29/05/17.
  */
trait NewEdge extends Edge with NewElement {

  final type PersistedVertexType = PersistedVertex

  final type NewVertexType = NewVertex

  final type VertexReference = Either[NewVertexType#TempId, PersistedVertexType#Id]

  final type Prop = DetachedProperty

}

object NewEdge {

  def apply(
    from: NewEdge#VertexReference,
    to: NewEdge#VertexReference,
    types: Set[NewEdge#TypeId],
    properties: NewEdge#Properties
  ): NewEdge = ImplNewEdge(from, to, types, properties)

  def unapply(edge: NewEdge): Option[(NewEdge#VertexReference, NewEdge#VertexReference, Set[NewEdge#TypeId], NewEdge#Properties)] = {
    if (edge eq null)
      None
    else
      Some(edge.from, edge.to, edge.types, edge.properties)
  }

}
