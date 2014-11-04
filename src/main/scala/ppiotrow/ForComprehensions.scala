package ppiotrow

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Inspired by http://arosien.github.io/lovely-for-comps/#slide1
 */
object ForComprehensions extends App {
  /**
   * Avoid match
   * Inline value declarations
   * RHS must have same shape
   * reflect.runtime.universe.reify {
   */
//  Experiment.run()
  //hard
  {
    val driverF = CarsWS.getDriver()
    val euroToPlnF = CurrencyWS.euroToPln()
    val carF = driverF.flatMap { driver =>
      val carId = driver.carId
      CarsWS.getCar(carId)
    }
    val value = carF.flatMap{ car =>
      euroToPlnF.map{euroToPln =>
       car.valuePLN * euroToPln
      }
    }
    value.map{v =>println(s"Will sell car for $v €")}
  }
  //or easier
  {
    val result = for(
      driver <- CarsWS.getDriver();
      carId = driver.carId;
      car <- CarsWS.getCar(carId);
      euroToPLN <- CurrencyWS.euroToPln();
      valueInEuro = euroToPLN * car.valuePLN
    ) yield s"Will sell car for $valueInEuro €"
    result.map(println)
  }
}

object Experiment {
  def run(): Unit = {
    val result =
      reflect.runtime.universe.reify {
      for {
        x <- Some(12)
        if x < 10
      } yield x + 1
    }.tree
    println(result)
  }
}

object DifferentShape {
  /**
   * F[_]
   */
  def run(): Unit = {
//    for {
//      x <- Some(12)
//      y <- List(2, 4)
//    } yield x * y
  }
}

object CarsWS {
  def getCar(id: Int): Future[Car] = Future.successful(Car(5000))
  def getDriver(): Future[Driver] = Future.successful(Driver(12))
}

object CurrencyWS {
  def euroToPln(): Future[Int] = Future.successful(4)
}
case class Car(valuePLN: Int)

case class Driver(carId: Int)
