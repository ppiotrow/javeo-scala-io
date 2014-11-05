package ppiotrow.scalazed

import scalaz._
import Scalaz._

object CathegoryFunctor {
  //A Functor is something that can be mapped:
  def addSix[F[_]](toAdd: F[Int])(implicit mapper: Functor[F]): F[Int] = {
    mapper.map(toAdd)(_ + 6)
  }

  val a = addSix(Option(12))
  println(a)

  val b = addSix(List(1,2,3))
  println(b)
}
