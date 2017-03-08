import com.affaince.subscription.testdata.generator.ProductTestDataGenerator
import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.http.Predef._

import scala.util.Random

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class Subscriber extends BaseSimulator {

  val productTestDataGenerator = new ProductTestDataGenerator().generate(5);
  productTestDataGenerator.getSubscriptionCount()

  val scn = scenario("Create Subscriber").exec(CreateSubscriber.createSubscriber)
      .repeat(1) {
        CreateSubscriber.setPassword
      }
    .repeat(1) {
        CreateSubscriber.createSubscription
    }
//    .repeat(5) {
//      CreateSubscriber.addItemToSubscription
//    }
//    .repeat(1) {
//      CreateSubscriber.confirmSubscription
//    }


  setUp(scn.inject(atOnceUsers(productTestDataGenerator.getSubscriptionCount())).protocols(http))


  //setUp(scn.inject(constantUsersPerSec(users.toDouble) during (duration.seconds))).protocols(http)
}

object CreateSubscriber {

  val createSubscriberUrl="http://localhost:8081/subscriber"
  val createSubscriptionUrl = "http://localhost:8081/subscription"
  val subscriberJsonFeeder = jsonFile ("Subscribers.json")

  val createSubscriber = feed(subscriberJsonFeeder)
      .exec(
        http ("Create Subscriber")
          .post(createSubscriberUrl)
          .body(
            StringBody(
              """
                |{
                |   "subscriberName": {"title":"${title}","firstName":"${firstName}",
                |    "middleName":"${middleName}",
                |    "lastName":"${lastName}"},
                |    "address":{"addressLine1":"${addressLine1}",
                |    "addressLine2":"${addressLine2}",
                |    "city":"${city}",
                |    "state":"${state}",
                |    "country":"${country}",
                |    "pinCode":"${pinCode}"},
                |    "contactDetails":{"email":"${email}",
                |    "mobileNumber":"${mobileNumber}",
                |    "alternativeNumber":"$alternativeNumber{}"}
                |}
              """.stripMargin
            )
          ).asJSON
          .check (jsonPath("$.id").saveAs("subscriberId"))
      )

  val setPassword = //exec(session => session.set("randomeName", Random.nextInt(5)))
      exec (
          http ("Set Subscriber Password")
        .put((createSubscriberUrl + "/password/${subscriberId}").el[String])
        .body(
          StringBody(
            """
              |{
              | "password":"password1"
              |}
            """.stripMargin
          )
        ).asJSON
  )

  val createSubscription = //feed (feeder)
    exec(
      http ("Create Subscription")
        .post(createSubscriptionUrl)
        .body(
          StringBody(
            """
              |{
              | "subscriberId":"${subscriberId}"
              |}
            """.stripMargin
          )
        ).asJSON
        .check(jsonPath("$.id").saveAs("subscriptionId"))
    )

  val addItemToSubscription = //feed (feeder)
    exec(
      http("Add Item To Subscription")
        .put((createSubscriptionUrl + "/additem/${subscriberId}").el[String])
        .body(
          StringBody (
            """
              |{
              |    "productId":"product01",
              |    "countPerPeriod":"2",
              |    "period":{"value":"2","unit":"WEEK"},
              |    "discountedOfferedPrice":"40",
              |    "offeredPriceWithBasketLevelDiscount":"30",
              |    "noOfCycles":"6"
              |}
            """.stripMargin
          )
        ).asJSON
    )

  val confirmSubscription =
    exec(
      http("Confirm Subscription")
        .put((createSubscriptionUrl + "/confirmsubscription/${subscriberId}").el[String])
    )
}
