package institute.ptest.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.util.Random
import institute.ptest.requests.CanvasRequest

object Scenarios {

    private val canvasIdFeeder = Iterator.continually({
        val startInt = 100000
        val endInt = 999999
        val randomInt = Random.nextInt((startInt - endInt) + 1)
        Map("id" -> (s"i$randomInt"))
    })

    val getCanvasScenario = scenario("Get all canvas entries")
        .exec(CanvasRequest.getAll.check(status.is(200)))

    def createAndDeleteCanvasScenario(pauseBeforeDeletion: Duration = 2 seconds) =
        scenario("Create and delete canvas entry")
            .feed(canvasIdFeeder)
            .exec(CanvasRequest.create
                .check(status.is(200))
                .check(jsonPath("$.id").saveAs("id"))
            )
            .exitHereIfFailed
            .pause(1 second)
            .feed(canvasIdFeeder)
            .exec(CanvasRequest.delete
                .check(status.is(200))
            )
}