import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Product extends BaseSimulator {

  var scn = scenario("Create Subscriber").exec(RegisterProduct.registerProduct)

  setUp(scn.inject(atOnceUsers(10)).protocols(http))
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