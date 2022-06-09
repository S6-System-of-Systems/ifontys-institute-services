package institute.ptest.requests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CanvasRequest {

    private val contentTypeHeader = Map("Content-Type" -> "application/json;charset=UTF-8")

    val id = "i873955"

    val getAll = http("get canvas").get("/canvas")

    val create = http("create canvas")
        .post("/canvas")
        .headers(contentTypeHeader)
        .body(StringBody(s"""{"id": "${id}","url": "https://api.fhict.nl/people/i873955","givenName": "Wilrik","surName": "De Loose","initials": "WR","displayName": "Loose,Wilrik W.R. De","mail": "w.deloose@fontys.nl","office": "TQ4","telephoneNumber": "+318855555","mobileNumber": "0622455555","photo": "https://api.fhict.nl/pictures/i873955.jpg","department": "Algemeen","title": "Medewerker FHICT","personalTitle": "loow01","affiliations": ["user","staff","teacher"],"updatedAt": "2022-05-09T07:15:10","uid": "db960d2013e59d73a0633678c63c027c288c5187","employeeId": "39221"}""")).asJson

    val delete = http("delete canvas")
        .delete(s"/canvas/${id}")
}