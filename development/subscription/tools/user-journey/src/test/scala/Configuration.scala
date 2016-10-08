import io.gatling.jms.Predef._

/**
  * Created by rbsavaliya on 04-03-2016.
  */
object Configuration {

  val createSubscriberUrl="http://localhost:8081/subscriber"

  val jmsConfig = jms
    .connectionFactoryName("ConnectionFactory")
    .url("tcp://localhost:61616")
    .credentials("admin", "admin")
    .disableAnonymousConnect
    .contextFactory(classOf[org.apache.activemq.jndi.ActiveMQInitialContextFactory].getName)
    .listenerCount(1)
    .usePersistentDeliveryMode
    .receiveTimeout(6000)
}
