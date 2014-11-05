package ppiotrow.scalazed

import scalaz._
import Scalaz._
/*
http://noelmarkham.github.io/scalaz-intro
 */

object Ex1 extends App {
  (1<0).option("Foo") //if(1<0) Some(Foo) else None

  "6".parseInt

  Option("Fooo") == ""
  //Option("Fooo") === ""

  val map = Map(1->"a", 2->"b").filterKeys(_ == "")
}
