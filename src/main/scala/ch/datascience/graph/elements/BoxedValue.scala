package ch.datascience.graph.elements

import ch.datascience.graph.types.DataType

/**
  * Created by johann on 27/04/17.
  */
sealed abstract class BoxedValue extends Element {

  type Self
  val self: Self

  def isValidValue: ValidValue[Self]

  final def dataType: DataType = this match {
    case _: StringValue => DataType.String
    case _: CharValue => DataType.Character
    case _: BooleanValue => DataType.Boolean
    case _: ByteValue => DataType.Byte
    case _: ShortValue => DataType.Short
    case _: IntValue => DataType.Integer
    case _: LongValue => DataType.Long
    case _: FloatValue => DataType.Float
    case _: DoubleValue => DataType.Double
  }

  @throws[java.lang.ClassCastException]
  def unboxAs[V : ValidValue]: V = self.asInstanceOf[V]

}

final case class StringValue(self: String) extends BoxedValue {
  type Self = String
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Self]]
}
final case class CharValue(self: Char) extends BoxedValue {
  type Self = Char
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Char]]
}
final case class BooleanValue(self: Boolean) extends BoxedValue {
  type Self = Boolean
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Boolean]]
}
final case class ByteValue(self: Byte) extends BoxedValue {
  type Self = Byte
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Byte]]
}
final case class ShortValue(self: Short) extends BoxedValue {
  type Self = Short
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Short]]
}
final case class IntValue(self: Int) extends BoxedValue {
  type Self = Int
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Int]]
}
final case class LongValue(self: Long) extends BoxedValue {
  type Self = Long
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Long]]
}
final case class FloatValue(self: Float) extends BoxedValue {
  type Self = Float
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Float]]
}
final case class DoubleValue(self: Double) extends BoxedValue {
  type Self = Double
  def isValidValue: ValidValue[Self] = implicitly[ValidValue[Double]]
}

object BoxedValue {

  def apply(x: String): BoxedValue = StringValue(x)
  def apply(x: Char): BoxedValue = CharValue(x)
  def apply(x: Boolean): BoxedValue = BooleanValue(x)
  def apply(x: Byte): BoxedValue = ByteValue(x)
  def apply(x: Short): BoxedValue = ShortValue(x)
  def apply(x: Int): BoxedValue = IntValue(x)
  def apply(x: Long): BoxedValue = LongValue(x)
  def apply(x: Float): BoxedValue = FloatValue(x)
  def apply(x: Double): BoxedValue = DoubleValue(x)

}
