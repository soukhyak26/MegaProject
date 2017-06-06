import java.util.concurrent.TimeUnit

import com.affaince.subscription.date.SysDate
import com.affaince.subscription.testdata.generator.ProductTestDataGenerator
import io.gatling.core.Predef._
import io.gatling.core.session.el._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import org.joda.time.LocalDate

import scala.util.Random

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class sysdate extends BaseSimulator {

  val productTestDataGenerator = new ProductTestDataGenerator().getSubscriptionCount();

  val scn = scenario("Create Subscriber").exec(SysDateTest.sysDateChange)

  setUp(scn.inject(atOnceUsers(productTestDataGenerator)).protocols(http))


  //setUp(scn.inject(constantUsersPerSec(users.toDouble) during (duration.seconds))).protocols(http)
}

object SysDateTest {

  val sysDateChangeUrl= "http://localhost:8086/sysdate"
  val subscriberJsonFeeder = jsonFile ("Subscribers.json")
  val sysdateFeeder = csv("sysdate.csv").queue;

  val sysDateChange = feed(sysdateFeeder).exec(
    http ("Change SysDate Time")
      .put(sysDateChangeUrl)
      .body(
        StringBody(
          """
            |{
            | "sysDate":"${sysDate}",
            | "sysDateTime":"${sysDateTime}"
            |}
          """.stripMargin
        )
      ).asJSON
  )
}
