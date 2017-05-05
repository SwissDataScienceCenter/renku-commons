package ch.datascience
package graph
package elements

import scala.language.higherKinds

/**
  * Base trait for records, i.e. elements that hold properties
  *
  * Properties can be validated (see package types).
  *
  * @tparam Key   key type
  * @tparam Value value type
  * @tparam Prop  property type
  */
trait Record[Key, +Value, +Prop <: Property[Key, Value, Prop]] extends Element {

  def properties: Properties[Key, Value, Prop]

}
