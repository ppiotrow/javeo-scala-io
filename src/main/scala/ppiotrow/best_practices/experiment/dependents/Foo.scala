package ppiotrow.best_practices.experiment.dependents

import ppiotrow.best_practices.experiment.E

case class A(b: B)
case class B(c: C)
case class C() {
  val foo = new E().sayHelloRunnable("")
}