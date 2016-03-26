package com.affaince.subscription.expensedistribution.Configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.expensedistribution.determinator.OperatingExpenseStrategyDeterminator;
import com.affaince.subscription.expensedistribution.query.DeliveryViewRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends RabbitMQConfiguration {

    @Autowired
    private CamelContext camelContext;
    @Autowired
    private DeliveryViewRepository deliveryViewRepository;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public OperatingExpenseStrategyDeterminator operatingExpenseStrategyDeterminator() {
        return new OperatingExpenseStrategyDeterminator();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                from("bean:deliveryViewRepository?method=findAll()").choice()
                        .when().simple("&{operatingExpenseStrategyDeterminator.decideOperatingExpenseStrategy}" +
                            "==com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.DEFAULT_STRATEGY")
                        .to("bean:defaultOperatingExpenseDistributionDeterminator").to("bean:publisher")
                        .when().simple("&{operatingExpenseStrategyDeterminator.decideOperatingExpenseStrategy}" +
                            "==com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.EXTRAPOLATION_BASED_STRATEGY")
                        .to("bean:extraPolationBasedOperatingExpenseDistributionDeterminator").to("bean:publisher")
                        .when().simple("&{operatingExpenseStrategyDeterminator.decideOperatingExpenseStrategy}" +
                            "==com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.FORECAST_BASED_STRATEGY")
                        .to("bean:extraPolationBasedOperatingExpenseDistributionDeterminator").to("bean:publisher");
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
}
