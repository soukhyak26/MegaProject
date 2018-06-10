import com.affaince.subscription.testdata.generator.ProductTestDataGenerator
import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.http.Predef._

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Product extends BaseSimulator {

  new ProductTestDataGenerator().generate(5);

  var scn = scenario("Create Product").exec(RegisterProduct.registerProduct).pause(10)
    .repeat(1) {
      AddConfigurationParameters.addConfigurationParameters
    }
    .repeat(1) {
      AddProjectionParameter.addProjectionParameter
    }
    .pause (80)
    .repeat(1) {
      RegisterOpeningPrice.registerPrice
    }

  var benefitscn = scenario("Add Benefit equation").exec (BenefitEquation.createBenefitEquation)

  //var scn2 = scenario("Business Provision").exec(BusinessProvision.SetProvosion)

  Thread.sleep(1000)
  setUp(scn.inject(atOnceUsers(5)).protocols(http), benefitscn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://159.89.170.246:8082/product/register"
  val createProductConfigUrl = "http://159.89.170.246:8082/product/config"
  val createProjectionUrl = "http://159.89.170.246:8082/forecast"
  val registerOpeningPriceUrl = "http://159.89.170.246:8082/pricing/openprice"
  val businessProvisionUrl = "http://159.89.170.246:8085/businessacount/setProvision"
  val benefitEquationUrl = "http://159.89.170.246:8084/benefits"


  val productDetailsJsonFeeder = jsonFile("productdetails.json")

  val registerProduct = feed(productDetailsJsonFeeder)
    .exec(
      http("Register Product")
        .post(createProductUrl)
        .body(
          StringBody(
            """
              |{
              |    "productName":"${productName}",
              |    "categoryId":"${categoryId}",
              |    "subCategoryId":"${subCategoryId}",
              |    "quantity":"${quantity}",
              |    "quantityUnit":"${quantityUnit}",
              |    "substitutes":${substitute},
              |    "complements":${complements},
              |    "productPricingCategory":1,
              |    "purchasePrice":"${purchasePrice}",
              |    "mrp":"${mrp}"
              |}
            """.stripMargin
          )
        ).asJSON
        .check(jsonPath("$.productId").saveAs("productId"))
    )
}

object AddProjectionParameter {
  val addProjectionParameter =
      exec(
      http("Add Projection Parameter to Product")
        .post((RegisterProduct.createProjectionUrl + "/manual/${productId}").el[String])
        .body(ElFileBody("${productId}.json")).asJSON
    )
}

object AddConfigurationParameters {

  val addConfigurationParameters = exec(
    http("Add Configuration Parameters to Product")
      .put((RegisterProduct.createProductConfigUrl + "/${productId}").el[String])
      .body(
        StringBody(
          """
            |{
            |    "demandCurvePeriod":{"value":"1","unit":"YEAR"},
            |    "targetChangeThresholdForPriceChange":0.1,
            |    "isCrossPriceElasticityConsidered":"false",
            |    "isAdvertisingExpensesConsidered":"false",
            |    "actualsAggregationPeriodForTargetForecast":30,
            |    "pricingOptions":0,
            |    "pricingStrategyType":0,
            |    "costHeaderTypes":["PURCHASE_PRICE_PER_UNIT", "FIXED_EXPENSE_PER_UNIT", "VARIABLE_EXPENSE_PER_UNIT"]
            |}
          """.stripMargin
        )
      ).asJSON
  )
}

object RegisterOpeningPrice {

  val openingPriceJsonFeeder = jsonFile("openingpricedetails.json")
  val registerPrice = feed(openingPriceJsonFeeder)
      .exec(
    http("Register opening price to Product")
      .post((RegisterProduct.registerOpeningPriceUrl + "/${productId}").el[String])
      .body(
        StringBody(
          """
            |{
            |    "openingPrice":${openingPrice}
            |}
          """.stripMargin
        )
      ).asJSON
  )
}

object BusinessProvision {
  val SetProvosion = exec(
      http("Set Business Provisioning")
        .post((RegisterProduct.businessProvisionUrl).el[String])
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

object BenefitEquation {
  val createBenefitEquation = exec(
    http("Create Benefit Equation")
      .post((RegisterProduct.benefitEquationUrl).el[String])
      .body(
        StringBody(
          """
            |{
              | "benefitEquation":"given 100 currency and 2 month = 3 point configure as (currentSubscriptionAmount/subscriptionValue/subscriptionPeriod)*currentSubscriptionPeriod eligible when currentSubscriptionAmount >= 1000 and (currentSubscriptionPeriod > 5 or totalLoyaltyPeriod > 5) apply as 1/2N, 3/4N delivery in 40, 60 proportion;",
              |	"activationStartTime":"2016-09-29",
              |	"activationEndTime":"2020-12-30"
            |}
          """.stripMargin
        )
      ).asJSON
  )
}