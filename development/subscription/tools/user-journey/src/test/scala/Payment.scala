import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by rbsavaliya on 05-03-2016.
  */
class Payment extends BaseSimulator {

  var paymentscn = scenario("Create Payment Scheme").exec(PaymentScheme.createPaymentScheme)

  setUp(paymentscn.inject(atOnceUsers(1)).protocols(http))
}

object PaymentScheme {

  val createPaymentSchemeUrl = "http://159.89.170.246:8086/payments/scheme"

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