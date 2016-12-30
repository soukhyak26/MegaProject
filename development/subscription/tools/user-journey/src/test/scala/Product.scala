import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.http.Predef._

import scala.util.Random

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Product extends BaseSimulator {

  var scn = scenario("Create Product").exec(RegisterProduct.registerProduct)
    .repeat(1) {
      AddConfigurationParameters.addConfigurationParameters
    }
    .repeat(6) {
      AddProjectionParameter.addProjectionParameter
    }
    .repeat(1) {
      RegisterOpeningPrice.registerPrice
    }

  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://localhost:8082/product/register"
  val createProductConfigUrl = "http://localhost:8082/productconfig"
  val createProjectionUrl = "http://localhost:8082/forecast"
  val registerOpeningPriceUrl = "http://localhost:8082/pricing/openprice"
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
              |    "productPricingCategory":0
              |}
            """.stripMargin
          )
        ).asJSON
        .check(jsonPath("$.id").saveAs("productId"))
    )
}

object AddProjectionParameter {
  val stepforecastJsonFileFeeder = jsonFile("stepforecast.json");
  val addProjectionParameter =
    feed(stepforecastJsonFileFeeder)
      .exec(
      http("Add Projection Parameter to Product")
        .put((RegisterProduct.createProjectionUrl + "/addforecast/${productId}").el[String])
        .body(
          StringBody(
            """
              |{
              |    "productForecastParameters":[{"startDate":"${startDate}", "endDate":"${endDate}",
              |    "purchasePricePerUnit":100,"MRP":150,
              |    "numberOfNewSubscriptions":1000,
              |    "numberOfChurnedSubscriptions":10,
              |    "productForecastStatus":1}]
              |}
            """.
              stripMargin
          )
        ).asJSON
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
            |    "pricingOptions":1,
            |    "pricingStrategyType":1
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
      .put((RegisterProduct.registerOpeningPriceUrl + "/${productId}").el[String])
      .body(
        StringBody(
          """
            |{
            |    "openingPrice":${openingPrice},
            |    "purchasePrice":${purchasePrice},
            |    "MRP":${mrp}
            |}
          """.stripMargin
        )
      ).asJSON
  )
}