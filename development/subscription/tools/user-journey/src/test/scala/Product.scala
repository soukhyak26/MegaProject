import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.session.el._

import scala.util.Random

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Product extends BaseSimulator {

  var scn = scenario("Create Subscriber").exec(RegisterProduct.registerProduct)
    .repeat(1) {
      AddProjectionParameter.addProjectionParameter
    }
    .repeat(1) {
      AddConfigurationParameters.addConfigurationParameters
    }

  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object RegisterProduct {

  val createProductUrl="http://localhost:8082/product"
  val feeder = csv ("product.csv").queue

  val registerProduct = exec(session => session.set("randomProductId", Random.nextInt(20000)))
    .exec(
      http ("Register Product")
        .post(createProductUrl)
        .body(
          StringBody(
            """
              |{
              |   "productId":${randomProductId},
              |    "productName":"Toothpaste",
              |    "categoryId":"cat01",
              |    "subCategoryId":"subcat01",
              |    "quantity":"500",
              |    "quantityUnit":"GM",
              |    "substitutes":["23","34","54"],
              |    "complements":["44","36","78"]
              |}
            """.stripMargin
          )
        ).asJSON
        .check (jsonPath("$.id").saveAs("productId"))
    )
}

object AddProjectionParameter {

  val addProjectionParameter = exec (
    http("Add Projection Parameter to Product")
      .put((RegisterProduct.createProductUrl + "/addprojectionparameters/${productId}").el[String])
      .body(
        StringBody(
          """
            |{
            |    "forecastedPriceParameter":{
            |        "purchasePricePerUnit":"30",
            |        "MRP":"40",
            |        "numberOfNewCustomersAssociatedWithAPrice":"1000",
            |        "numberOfChurnedCustomersAssociatedWithAPrice":"50"
            |    },
            |    "demandDensity":"1.2",
            |    "averageDemandPerSubscriber":"2",
            |    "totalDeliveriesPerPeriod":"1",
            |    "averageWeightPerDelivery":"500"
            |}
          """.stripMargin
        )
      ).asJSON
  )
}

object AddConfigurationParameters {

  val addConfigurationParameters = exec (
    http("Add Configuration Parameters to Product")
      .put((RegisterProduct.createProductUrl + "/setproductconfig/${productId}").el[String])
      .body(
        StringBody(
          """
            |{
            |    "demandCurvePeriod":{"value":"1","unit":"MONTH"},
            |    "revenueChangeThresholdForPriceChange":"10",
            |    "isCrossPriceElasticityConsidered":"true",
            |    "isAdvertisingExpensesConsidered":"true",
            |    "demandWiseProfitSharingRules":[{"demandDensityPercentage":"10","sharedProfitPercentage":"20"},
            |    {"demandDensityPercentage":"15","sharedProfitPercentage":"30"}]
            |}
          """.stripMargin
        )
      ).asJSON
  )
}