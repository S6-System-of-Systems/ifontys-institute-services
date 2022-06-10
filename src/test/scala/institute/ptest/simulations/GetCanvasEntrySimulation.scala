package institute.ptest.simulations

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

import institute.ptest.config.Config.getBaseUrl

import institute.ptest.scenarios.Scenarios._

class GetCanvasEntrySimulation extends Simulation {

    val duration = System.getProperty("duration", "30").toInt seconds

    val userRate = System.getProperty("userRate", "50").toDouble

    val environment = System.getProperty("environment", "local")

    def httpProtocol = http.baseUrl(getBaseUrl(environment)).userAgentHeader("Gatling/test")

    before {
        println(s"Running GetCanvasEntrySimulation against ${getBaseUrl(environment)}")
    }

    setUp(getCanvasScenario.inject(constantUsersPerSec(userRate) during duration)).protocols(httpProtocol)
}