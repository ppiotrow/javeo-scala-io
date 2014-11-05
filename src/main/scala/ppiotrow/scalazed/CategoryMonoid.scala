package ppiotrow.scalazed

import scalaz._
import Scalaz._

object CategoryMonoid extends App {
//  A Monoid is a structure with an identity element and an associative binary operation
  //6 + 0 === 6
  //6 * 1 === 6


  val r = Option(1) |+| None |+| Option(2) //Option(1).map{x => None.map{y => x + y}.map{z => Option(2).map(z+_}}
  println(s"r = $r")

  val m1 = Map(1 -> List("A", "B", "C"), 2 -> List("AA", "BB"))
  val m2 = Map(1 -> List("Z"), 3 -> List("YYY"))

  val m12 = m1 |+| m2
  println(m12)

  val sum = List(Some(5), None, Some(3)).concatenate
  println(sum)

  val multiplication = Tags.Multiplication(5) |+| Tags.Multiplication(7)

  println(s"multiplication = $multiplication")
}
