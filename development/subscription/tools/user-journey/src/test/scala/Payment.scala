import com.affaince.subscription.testdata.generator.ProductTestDataGenerator
import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.http.Predef._

import scala.util.Random

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Payment extends BaseSimulator {

  var scn = scenario("Create Payment Scheme").exec(PaymentScheme.createPaymentScheme)

  setUp(scn.inject(atOnceUsers(1)).protocols(http))
}

object PaymentScheme {

  val createPaymentSchemeUrl = "http://localhost:8086/paymentscheme/create"

  val createPaymentScheme = exec(
      http("Create Payment Scheme")
        .post(createPaymentSchemeUrl)
        .body(
          StringBody(
            """
              |{
              |	"paymentSchemeName":"20%AdvancePayment",
              |	"paymentSchemeDescription":"20% advance payment and default destribution of other payment",
              |	"paymentSchemeRule":"pay 20% of current subscription amount on subscription confirmation and pay residual due amount after 1/2remaining-N, 3/4remaining-N delivery in default proportion",
              |	"startDate":"2017-07-15",
              |	"endDate":"2019-07-15"
              |}
            """.stripMargin
          )
        ).asJSON
    )
}