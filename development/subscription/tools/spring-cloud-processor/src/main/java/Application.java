import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;

/**
 * Created by mandar on 8/15/2017.
 */
@EnableBinding(Processor.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }
}