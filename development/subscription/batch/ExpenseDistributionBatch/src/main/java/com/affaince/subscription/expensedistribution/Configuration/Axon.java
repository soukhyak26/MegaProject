package com.affaince.subscription.expensedistribution.Configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.CommonConfig;
import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.determinator.OperatingExpenseStrategyDeterminator;
import com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent;
import com.affaince.subscription.expensedistribution.processor.*;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.camel.language.simple.SimpleLanguage.simple;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends CommonConfig {

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public OperatingExpenseStrategyDeterminator operatingExpenseStrategyDeterminator() {
        return new OperatingExpenseStrategyDeterminator();
    }

    @Bean
    public DefaultOperatingExpenseDistributionDeterminator defaultOperatingExpenseDistributionDeterminator () {
        return new DefaultOperatingExpenseDistributionDeterminator();
    }

    @Bean
    public ExtraPolationBasedOperatingExpenseDistributionDeterminator extraPolationBasedOperatingExpenseDistributionDeterminator () {
        return new ExtraPolationBasedOperatingExpenseDistributionDeterminator();
    }

    @Bean
    public ForecastBasedOperatingExpenseDistributionDeterminator forecastBasedOperatingExpenseDistributionDeterminator () {
        return new ForecastBasedOperatingExpenseDistributionDeterminator();
    }

    @Bean
    public ExpenseDistributionClient expenseDistributionClient () {
        return new ExpenseDistributionClient();
    }

    @Bean
    public CalculatePerUnitExpense calculatePerUnitExpense () {
        return new CalculatePerUnitExpense();
    }

    @Bean
    public ProductWiseDeliveryStatsAggregation productWiseDeliveryStatsAggregation () {
        return new ProductWiseDeliveryStatsAggregation();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                from("timer://foo?repeatCount=1")
                        .to("bean:defaultOperatingExpenseDistributionDeterminator")
                        //.to("bean:productWiseDeliveryStatsAggregation")
                        //.to ("bean:calculatePerUnitExpense")
                        .split(body(SubscriptionSpecificOperatingExpenseCalculatedEvent.class))
                        .to("bean:publisher");
            }
        };
    }

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {
                System.out.println("@@@@@@@@@@@Hey Camel Started@@@@@@@@@@@");
            }
        };
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {};
    }
}
