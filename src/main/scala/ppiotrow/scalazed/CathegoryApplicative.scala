package ppiotrow.scalazed

import scalaz._
import Scalaz._
import scalaz.syntax._

object CathegoryApplicative extends App {

  val a = (Option(15) |@| Some(10) |@| Some(5)) apply {_+_+_}
  println(s"a = $a")

  val person = (Option("John") |@| Option(10)) apply Person.apply
  println(person)

  val noperson = (None |@| Option(10)) apply Person.apply
  println(noperson)
}

case class Person(name: String, age: Int)
