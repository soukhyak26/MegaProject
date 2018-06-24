import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Business extends BaseSimulator {

  var businessscn = scenario("Create configure Business Domain").exec(BusinessAccount.createBusinessAccount)
    .pace(10)
    .repeat(1) {
        BusinessAccount.configureBusinessAccount
      }

  setUp(businessscn.inject(atOnceUsers(1)).protocols(http))
}

object BusinessAccount {

  val createBusinessAccountUrl = "http://159.89.170.246:8085/business/account"
  val configureBusinessAccountUrl = "http://159.89.170.246:8085/business/configure"

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
}