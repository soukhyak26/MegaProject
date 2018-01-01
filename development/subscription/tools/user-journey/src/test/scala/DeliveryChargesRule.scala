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
  val setDeliveryChargesRulesUrl = "http://localhost:8081/deliverychargerules"

  val setDeliveryChargesRules = exec(
    http("Create Basket Rules")
      .post(setDeliveryChargesRulesUrl)
      .body(
        StringBody(
          """
            |{
            |   "ruleId":"0",
            |   "deliveryChargesRules":[{"ruleHeader":"1KG",
            |   "ruleMinimum":"0",
            |   "ruleMaximum":"1",
            |   "ruleUnit":"GM",
            |   "applicableValue":10},{"ruleHeader":"2KG",
            |   "ruleMinimum":"1",
            |   "ruleMaximum":"2",
            |   "ruleUnit":"GM",
            |   "applicableValue":20},{"ruleHeader":"3KG",
            |   "ruleMinimum":"2",
            |   "ruleMaximum":"3",
            |   "ruleUnit":"GM",
            |   "applicableValue":30},{"ruleHeader":"4KG",
            |   "ruleMinimum":"3",
            |   "ruleMaximum":"4",
            |   "ruleUnit":"GM",
            |   "applicableValue":40},{"ruleHeader":"5KG",
            |   "ruleMinimum":"4",
            |   "ruleMaximum":"5",
            |   "ruleUnit":"GM",
            |   "applicableValue":50}]
            |}
          """.stripMargin
        )
      ).asJSON
  )
}
