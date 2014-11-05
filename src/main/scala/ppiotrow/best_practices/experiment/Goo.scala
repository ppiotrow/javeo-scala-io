package ppiotrow.best_practices.experiment

class E {
  def sayHelloRunnable(name: String): Runnable = new Runnable {
    def sayIt() = println(s"Hello, $name")
    def other() = println(s"recompile everything")
    def run() = sayIt()
  }
}
