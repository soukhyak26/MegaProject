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
      AddProjectionParameter.addProjectionParameter
    }
    .repeat(1) {
      AddConfigurationParameters.addConfigurationParameters
    }

  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl = "http://localhost:8082/product/register"
  val createProductConfigUrl = "http://localhost:8082/productconfig"
  val createProjectionUrl = "http://localhost:8082/forecast"
  val feeder = csv("productdetails.csv").queue

  val registerProduct = feed(feeder)
    .exec(
      http("Register Product")
        .post(createProductUrl)
        .body(
          StringBody(
            """
              |{
              |    "productName":"${productName}",
              |    "categoryId":"${categoryID}",
              |    "subCategoryId":"${subCategoryId}",
              |    "quantity":"${quantity}",
              |    "quantityUnit":"${quantityUnit}",
              |    "substitutes":${substitutes},
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
  val feeder1 = csv("projectionparameter.csv").queue
  val addProjectionParameter =
    feed(feeder1)
      .exec(
      http("Add Projection Parameter to Product")
        .put((RegisterProduct.createProjectionUrl + "/addforecast/${productId}").el[String])
        .body(
          StringBody(
            """
              |{
              |    "productForecastParameters":[{"startDate":"${forecastStartDate}",
              |    "endDate":"${forecastEndDate}",
              |    "purchasePricePerUnit":${purchasePricePerUnit},"MRP":${MRP},
              |    "numberOfNewSubscriptions":${numberOfNewSubscriptions},
              |    "numberOfChurnedSubscriptions":${numberOfChurnedSubscriptions},
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