import io.gatling.app.Gatling
import io.gatling.core.config.GatlingPropertiesBuilder

/**
  * Created by rbsavaliya on 04-03-2016.
  */
class Simulator {

  def main(args: Array[String]) {

    val simClass = classOf[Subscriber].getName

    val props = new GatlingPropertiesBuilder
    props.sourcesDirectory("./src/test/scala")
    props.binariesDirectory("./target/test-classes")
    props.simulationClass(simClass)
    props.outputDirectoryBaseName("./target/gatling/result")

    Gatling.fromMap(props.build)
  }
}
