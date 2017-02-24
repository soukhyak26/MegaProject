import java.io.{ByteArrayOutputStream, ObjectOutputStream}
import javax.jms._

import Configuration.jmsConfig
import com.affaince.subscription.testdata.generator.com.affaince.subscription.benefits.command.event.BenefitAddedEvent
import io.gatling.core.Predef._
import io.gatling.jms.Predef._

class TestJmsEvent extends BaseSimulator {
  val bos = new ByteArrayOutputStream();

  val out = new ObjectOutputStream(bos);
  out.writeObject(new BenefitAddedEvent("1", "2", "3"));
  out.flush();
  val scn = scenario("JMS DSL test").repeat(1) {
    exec(
      jms("req").reqreply.destination(topic("VirtualTopic.EventBus"))
        .bytesMessage(bos.toByteArray())
        .property("JMSType", "com.affaince.subscription.benefits.command.event.BenefitAddedEvent")
    )
  }

  setUp(scn.inject(atOnceUsers(1)))
    .protocols(jmsConfig)

  def checkBodyTextCorrect(m: Message) = {
    // this assumes that the service just does an "uppercase" transform on the text
    m match {
      case tm: TextMessage => tm.getText == "HELLO FROM GATLING JMS DSL"
      case _ => false
    }
  }
}