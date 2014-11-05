package ppiotrow.scalazed


import scalaz._
import Scalaz._
import scalaz.Validation


object ScalazValidation extends App {
//When Validation is constructed with an error type that has a Semigroup,
// there exists an Applicative Functor for Validation that accumulates errors
  val a = Validation.success("ABC")
  val b = Validation.failure("Blad")

  import scalaz.syntax.validation._
  val c = 40.success
  val d = "errors.input.form.age".failure

  val e = "43".parseInt
}

object ScalazExampleValidation extends App {
  import java.util.UUID
  import scalaz.Validation
  import Validation._
  import scalaz.syntax.std.option._
  import scalaz.syntax.bifunctor._

  def justMessage[S](v: Validation[Throwable, S]): Validation[String, S] =
    v.<-: { _.getMessage }

  def extractId(metadata: Map[String, String]): Validation[String, UUID] =
    for {
      str <- metadata.get("id").toSuccess("No id property")
      id <- justMessage(fromTryCatchNonFatal(UUID.fromString(str)))
    } yield id

  println(extractId(Map.empty))
  println(extractId(Map("id" -> "notUuid")))
  println(extractId(Map("id" -> UUID.randomUUID.toString)))
}