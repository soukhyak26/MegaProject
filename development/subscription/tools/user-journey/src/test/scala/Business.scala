import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Business extends BaseSimulator {

  var businessscn = scenario("Create configure Business Domain").exec(BusinessAccount.createBusinessAccount)
    .pause(10)
    .repeat(1) {
        BusinessAccount.configureBusinessAccount
    }
    .pause(5)
    .repeat(1) {
      BusinessAccount.SetProvosion
    }

  setUp(businessscn.inject(atOnceUsers(1)).protocols(http))
}

object BusinessAccount {

  val createBusinessAccountUrl = "http://159.89.170.246:8085/business/account"
  val configureBusinessAccountUrl = "http://159.89.170.246:8085/business/configure"
  val businessProvisionUrl = "http://159.89.170.246:8085/businessacount/setProvision"

  val createBusinessAccount = exec(
      http("Create Business Account")
        .post(createBusinessAccountUrl)
        .body(
          StringBody(
            """
              |{
              |  "merchantId": "merchant2",
              |  "startDate": "2018-01-01",
              |  "endDate": "2018-12-31",
              |  "dateOfProvision": "2018-01-01"
              |}
            """.stripMargin
          )
        ).asJSON
    )

  val configureBusinessAccount = exec(
    http("Configure Business Account")
      .post(configureBusinessAccountUrl)
      .body(
        StringBody(
          """
            |{
            |  "merchantId": "merchant2",
            |  "budgetAdjustmentOptions": "ACCEPT_AUTOMATED_BUDGET_ADJUSTEMENT",
            |  "startDateOfFinancialYear": "2018-01-01",
            |  "endDateOfFinancialYear": "2018-12-31",
            |  "taxAsPercentageOfAnnualRevenue": 0.2
            |}
          """.stripMargin
        )
      ).asJSON
  )

  val SetProvosion = exec(
    http("Set Business Provisioning")
      .post(businessProvisionUrl)
      .body(
        StringBody(
          """
            |{
            |    "provisionForPurchaseCost":"100000.0",
            |    "provisionForLosses":"50000.0",
            |    "provisionForBenefits":"25000.0",
            |    "provisionForTaxes":"20000.0",
            |    "provisionForOthers":"10000.0",
            |    "provisionForCommonExpenses":"10000.0",
            |    "provisionForSubscriptionSpecificExpenses":"50000.0",
            |    "provisionDate":"2016-04-29"
            |}
          """.stripMargin
        )
      ).asJSON
  )
}