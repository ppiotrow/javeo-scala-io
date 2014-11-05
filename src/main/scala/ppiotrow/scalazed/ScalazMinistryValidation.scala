package ppiotrow.scalazed
import scalaz.Validation
import Validation._
import scalaz._
import Scalaz._
/**
 * http://www.slideshare.net/Kobib9/the-good-parts-in-scalaz
 */
case class Employee(name: String, age: Int, yearsInSchool: Int, citizenship: Boolean)

trait PublicInstitute {
  def checkEducation(p: Employee): Validation[String, Employee] = {
    if(p.yearsInSchool < 12) "education.not.enough".failure else p.success
  }
  def checkCitizenship(p: Employee): Validation[String, Employee] = {
    if(p.citizenship) p.success else "citizenship.required".failure
  }
  def checkAge(p: Employee): Validation[String, Employee] = {
    if(p.age < 18) "age.to.young".failure
    else if(p.age >65) "age.to.old".failure else  p.success
  }
}
object ScalazMinistryValidation extends App with PublicInstitute  {

  def applyForJob(e: Employee): Validation[String, String] = {
    for{
      v1 <- checkEducation(e)
      v2 <- checkCitizenship(e)
      v3 <- checkAge(e)
    } yield s"${v1.name}, you got the job"
  }
  println(applyForJob(Employee("John", 10, 15, citizenship = false)))
}

object ScalazValidationAccumulation extends App {
  val v1 = "age.to".successNel[String]

  val v2 = "age.to.young".failureNel[String]

  val v3 = "to.stupid".failureNel[String]

  val result = v1|@| v2|@| v3 apply {_ + _ + _}

  result.fold(println, println)
  }
