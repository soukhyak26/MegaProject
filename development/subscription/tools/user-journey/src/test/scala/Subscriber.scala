import java.util.concurrent.TimeUnit

import com.affaince.subscription.testdata.generator.ProductTestDataGenerator
import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.http.Predef._

import scala.util.Random

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class Subscriber extends BaseSimulator {

  val productTestDataGenerator = new ProductTestDataGenerator().getSubscriptionCount();

  val scn = scenario("Create Subscriber").exec(CreateSubscriber.createSubscriber)
      .pause(10)
      .repeat(1) {
        CreateSubscriber.setPassword
      }
    .repeat(1) {
        CreateSubscriber.createSubscription
    }
    .repeat(1) {
      CreateSubscriber.addItemToSubscription
    }.pause(5)
    .repeat(1) {
      CreateSubscriber.confirmSubscription
    }

  val scenario2 = scenario("Add Delivery Charges").exec(SetDeliveryChargesRules.setDeliveryChargesRules)
  setUp(scn.inject(atOnceUsers(productTestDataGenerator)).protocols(http), scenario2.inject(atOnceUsers(1)).protocols(http))


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

  val createSubscription =
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

  val addItemToSubscription =
    exec(
      http("Add Item To Subscription")
        .put((createSubscriptionUrl + "/addsubscription/${subscriberId}").el[String])
        .body(
          ElFileBody ("${subscriberId}.json")
        ).asJSON
    )

  val confirmSubscription =
    exec(
      http("Confirm Subscription")
        .put((createSubscriptionUrl + "/confirmsubscription/${subscriberId}").el[String])
    )
}
