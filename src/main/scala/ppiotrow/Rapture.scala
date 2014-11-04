package ppiotrow

import rapture.json.Json
import rapture.json.jsonBackends.jackson._

object Rapture extends App{
  {
    val javeotalk =
      s"""
       |{
       |"subject": "scala.io",
       |"speaker": "Przemek",
       |"deep": {"deeper":"Great"}
       |}""".stripMargin

    val parsed = Json.parse(javeotalk)
    val subject = parsed.subject.as[String]
    val deepest = parsed.deep.deeper.as[String]
    println(s"$subject was $deepest")
  }

  {
    val javeotalksJson = s"""
     |[
     |{"subject": "scala.io",
     |"speaker": "Przemek"},
     |{"speaker": "scala.io",
     |"subject": "Przemek"}
     |]
   """.stripMargin
    val parsed = Json.parse(javeotalksJson)
    val javeotalks = parsed.as[List[Javeotalk]]
    val wrong = parsed(0).as[Javeotalk]
    println(wrong)
    println(javeotalks) // speaker and subject changed
  }
}

case class Javeotalk(subject: String, speaker: String)

