import com.affaince.subscription.business.distribution.db.DefaultShippingProfileRepositoryMock;
import com.affaince.subscription.business.distribution.db.DeliveryForecastViewRepositoryMock;
import com.affaince.subscription.business.distribution.sampler.DeliveriesDistributionProfile;
import com.affaince.subscription.business.distribution.sampler.Period;
import com.affaince.subscription.business.distribution.strategy.DefaultDistributionStrategy;
import com.affaince.subscription.business.distribution.strategy.DistributionStrategy;

import java.util.Map;


/*
@SpringBootApplication
@ComponentScan({"com.affaince.distribution"})
@PropertySource({"classpath:application.properties", "classpath:sysdatesetting.properties"})
*/
public class Application {

    public static void main(String[] args) {
/*
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run();
*/
        DeliveryForecastViewRepositoryMock.build();
        DefaultShippingProfileRepositoryMock.build();
        DistributionStrategy distributionStrategy = new DefaultDistributionStrategy();
        Map<Period, DeliveriesDistributionProfile> periodWiseDeliveriesDistributionProfilesMap = distributionStrategy.distributeDistributionExpenseAcrossProducts("1");
        for (Period period : periodWiseDeliveriesDistributionProfilesMap.keySet()) {
            System.out.println("Start ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(periodWiseDeliveriesDistributionProfilesMap.get(period));
            System.out.println("End ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        }
    }
}
