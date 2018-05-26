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

  val createBasketRulesUrl = "http://159.89.170.246:8081/subscriptionrules"

  val createBasketRules = exec(
    http("Create Basket Rules")
      .post(createBasketRulesUrl)
      .body(
        StringBody(
          """
            |{
            |    "maximumPermissibleAmount":"5001",
            |    "minimumAmountForDiscountEligibility":"300",
            |    "maximumPermissibleDiscount":"20",
            |    "maximumPermissibleDiscountUnit":"2",
            |    "minimumAmountEligibleForFreeShipping":"",
            |    "diffBetweenDeliveryPreparationAndDispatchDate":1,
            |    "subscriptionValueRanges":[{"minimumValue":"10000","maximumValue":"19999"},{"minimumValue":"20000","maximumValue":"39999"},{"minimumValue":"40000","maximumValue":"59999"}]
            |}
          """.stripMargin
        )
      ).asJSON
      .check(jsonPath("$.id").saveAs("subscriptionRulesId"))
  )
}
