package ch.datascience.service.models.projects

import ch.datascience.graph.Constants.VertexId

case class SimpleProject(
    id:     VertexId,
    name:   String,
    labels: Set[String]
)
