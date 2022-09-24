import com.affaince.accounting.client.AccountingClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    public AccountingClient getAccountingClient(){
        return  new AccountingClient();
    }

    @Override
    public void run(String... args) throws Exception {
        getAccountingClient().investCapital();

    }
}
