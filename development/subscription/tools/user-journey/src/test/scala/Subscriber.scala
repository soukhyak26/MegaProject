import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.session.el._

import scala.util.Random

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class Subscriber extends BaseSimulator {

  val scn = scenario("Create Subscriber").exec(CreateSubscriber.createSubscriber)
      .repeat(1) {
        CreateSubscriber.setPassword
      }
    .repeat(1) {
        CreateSubscriber.createSubscription
    }
    .repeat(5) {
      CreateSubscriber.addItemToSubscription
    }


  setUp(scn.inject(atOnceUsers(10)).protocols(http))


  //setUp(scn.inject(constantUsersPerSec(users.toDouble) during (duration.seconds))).protocols(http)
}

object CreateSubscriber {

  val createSubscriberUrl="http://localhost:8081/subscriber"
  val createSubscriptionUrl = "http://localhost:8081/subscription"
  val feeder = csv ("subscriber.csv").queue

  val createSubscriber = exec(session => session.set("randomeName", Random.nextInt(5)))
      .exec(
        http ("Create Subscriber")
          .post(createSubscriberUrl)
          .body(
            StringBody(
              """
                |{
                |   "subscriberName": {"title":"Mr.","firstName":"Rahul",
                |    "middleName":"Babulal",
                |    "lastName":"Savaliya"},
                |    "address":{"addressLine1":"A1-504, Casa 7",
                |    "addressLine2":"Dange Chowk",
                |    "city":"Pune",
                |    "state":"MH",
                |    "country":"India",
                |    "pinCode":"411033"},
                |    "contactDetails":{"email":"rahulsavaliya@gmail.com",
                |    "mobileNumber":"9011073762",
                |    "alternativeNumber":"9028185884"}
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
              | "userId":"${subscriberId}"
              |}
            """.stripMargin
          )
        ).asJSON
        .check(jsonPath("$.id").saveAs("subscriptionId"))
    )

  val addItemToSubscription = feed (feeder)
    .exec(
      http("Add Item To Subscription")
        .put ((createSubscriptionUrl + "/additem/${subscriptionId}").el[String])
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
}
