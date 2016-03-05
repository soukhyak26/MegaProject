import io.gatling.http.Predef._
import io.gatling.core.Predef._

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class BaseSimulator extends Simulation {

  val httpConf = http.disableWarmUp.basicAuth("abc","abc")
  val users = Integer.getInteger("users", 1);
  val duration = Integer.getInteger("duration", 10)
}
