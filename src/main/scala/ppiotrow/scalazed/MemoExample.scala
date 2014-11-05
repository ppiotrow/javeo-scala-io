package ppiotrow.scalazed

import scalaz._
import Scalaz._

object MemoExample extends App{
  val memoizedFib: Int => Int = Memo.mutableHashMapMemo {
    case 0 => 0
    case 1 => 1
    case n => memoizedFib(n - 2) + memoizedFib(n - 1)
  }

  println(memoizedFib(45))
}
