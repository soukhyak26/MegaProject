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

  val createBasketRulesUrl = "http://localhost:8081/basketrules"

  val createBasketRules = exec(
    http("Create Basket Rules")
      .post(createBasketRulesUrl)
      .body(
        StringBody(
          """
            |{
            |   "maximumPermissibleAmount":"10000",
            |   "minimumAmountForDiscountEligibility":"2000",
            |   "maximumPermissibleDiscount":"10",
            |   "maximumPermissibleDiscountUnit":"1",
            |   "minimumAmountEligibleForFreeShipping":"1000"
            |}
          """.stripMargin
        )
      ).asJSON
      .check(jsonPath("$.id").saveAs("basketRulesId"))
  )
}
