package ppiotrow.best_practices

/**
 *  Public functions SHOULD have an explicit return type
 *
 * As a side-effect, this also increases compilation times, as whenever sayHelloRunnable changes implementation,
 * it also changes the signature so everything that depends on it must be recompiled.
 */


class ExplicitType {
  def sayHelloRunnable(name: String) = new Runnable {
    def sayIt() = println(s"Hello, $name")
    //def xxxIt() = println(s"Uncomment to recompile A, B, C")
    def run() = sayIt()
  }
  val foo = sayHelloRunnable("")
}
/*
Classes to recompile
 */
  case class A(b: B)

  case class B(c: C)

  case class C() {
    val foo = new ExplicitType().sayHelloRunnable("")
  }
