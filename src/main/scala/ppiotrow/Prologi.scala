package ppiotrow

import shapeless._
import nat._
import ops.nat._
import ops.hlist._

/**
 * Code from
 * https://github.com/folone/2014-scala.io/blob/master/src/main/scala/com.soundcloud/two/hanoi.scala
 * https://speakerdeck.com/folone/prolog-prolog-scala
 */
object Hanoi {

  // Show typeclass
  trait Show[T] {
    def show: String
  }

  // Color utilities
  def wrap(str: String, color: String) = color ++ str ++ "\u001B[0m"

  def green(str: String) = wrap(str, "\u001B[32m")

  def yellow(str: String) = wrap(str, "\u001B[33m")

  def blue(str: String) = wrap(str, "\u001B[34m")

  sealed abstract trait Name

  case object Left extends Name

  case object Right extends Name

  case object Center extends Name

  implicit val leftShow: Show[Left.type] = new Show[Left.type] {
    def show = yellow("Left")
  }
  implicit val rightShow: Show[Right.type] = new Show[Right.type] {
    def show = green("Right")
  }
  implicit val centerShow: Show[Center.type] = new Show[Center.type] {
    def show = blue("Center")
  }

  trait Move[N <: Nat, L <: Name, R <: Name, C <: Name] {
    val acc: String
  }

  object Move {
    // Direct translation of prolog code.
    implicit def move0[L <: Name,
    R <: Name,
    C <: Name]
    (implicit ev0: Show[L],
     ev1: Show[R]):
    Move[_1, L, R, C] =
      new Move[_1, L, R, C] {
        val acc = "Move top disk from\t" + ev0.show + "\tto\t" + ev1.show
      }

    implicit def move1[N <: Nat,
    M <: Nat,
    L <: Name,
    R <: Name,
    C <: Name]
    (implicit ev0: LT[_1, N],
     ev1: Diff.Aux[N, _1, M],
     ev2: Move[M, L, C, R],
     ev3: Move[_1, L, R, C],
     ev4: Move[M, C, R, L],
     ev5: Show[Move[M, L, C, R]],
     ev6: Show[Move[_1, L, R, C]],
     ev7: Show[Move[M, C, R, L]]):
    Move[N, L, R, C] =
      new Move[N, L, R, C] {
        val acc = ev5.show ++ "\n" ++ ev6.show ++ "\n" ++ ev7.show
      }

    // Show instance to actually see the result.
    implicit def moveShow[N <: Nat,
    L <: Name,
    R <: Name,
    C <: Name]
    (implicit ev: Move[N, L, R, C]) =
      new Show[Move[N, L, R, C]] {
        def show = ev.acc
      }
  }

}
object Main extends App {
  import Hanoi._
  println(implicitly[Show[Move[_6, Left.type, Right.type, Center.type]]].show)
}
//
//object HanoiProlog {
//  sealed abstract trait Name
//  case object Left extends Name
//  case object Right extends Name
//  case object Center extends Name
//  trait Move[N <: Nat, L <: Name, R <: Name, C <: Name]
//  object Move {
//    // Direct translation of prolog code.
//    implicit def move0[L <: Name,
//    R <: Name,
//    C <: Name]:
//    Move[_1, L, R, C] =
//      new Move[_1, L, R, C] {}
//
//    implicit def move1[N <: Nat,
//    M <: Nat,
//    L <: Name,
//    R <: Name,
//    C <: Name]
//    (implicit ev0 : LT[_1, N],
//     ev1 : Diff.Aux[N, _1, M],
//     ev2 : Move[M, L, C, R],
//     ev3 : Move[_1, L, R, C],
//     ev4 : Move[M, C, R, L]):
//    Move[N, L, R, C] =
//      new Move[N, L, R, C] {}
//  }
//  // implicitly[Move[_3, Left.type, Right.type, Center.type]]
//}