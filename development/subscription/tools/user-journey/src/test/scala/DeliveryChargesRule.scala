import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by rsavaliya on 17/4/16.
  */
class DeliveryChargesRule extends BaseSimulator {
  var scn = scenario("Create Basket Rules").exec(SetDeliveryChargesRules.setDeliveryChargesRules)
  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object SetDeliveryChargesRules {
  val setDeliveryChargesRulesUrl = "http://localhost:8082/deliverychargerules"

  val setDeliveryChargesRules = exec(
    http("Create Basket Rules")
      .post(setDeliveryChargesRulesUrl)
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
