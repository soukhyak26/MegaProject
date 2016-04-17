import io.gatling.core.Predef._
import io.gatling.http.Predef._


/**
  * Created by rsavaliya on 17/4/16.
  */
class BasketRules extends BaseSimulator {
  var scn = scenario("Create Basket Rules").exec(CreateBasketRules.createBasketRules)
  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object CreateBasketRules {

  val createBasketRulesUrl = "http://localhost:8082/basketrules"

  val createBasketRules = exec(
    http("Create Basket Rules")
      .post(createBasketRulesUrl)
      .body(
        StringBody(
          """
            |{
            |   "ruleId":"1",
            |   "deliveryChargesRules":"[{"ruleHeader":"1KG",
            |   "ruleMinimum":"0",
            |   "ruleMaximum":"1",
            |   "ruleUnit":"GM",
            |   "applicableValue":""}]"
            |}
          """.stripMargin
        )
      ).asJSON
  )
}
