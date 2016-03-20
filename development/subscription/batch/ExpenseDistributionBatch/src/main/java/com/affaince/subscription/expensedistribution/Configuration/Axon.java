package com.affaince.subscription.expensedistribution.Configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.expensedistribution.determinator.OperatingExpenseStrategyDeterminator;
import com.affaince.subscription.expensedistribution.processor.DeliveryExpenseProcessor;
import com.affaince.subscription.expensedistribution.processor.ProductWiseExpenseDistributionProcessor;
import com.affaince.subscription.expensedistribution.processor.ProductWiseTotalDeliveryExpenseStrategy;
import com.affaince.subscription.expensedistribution.query.DeliveryView;
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
                //INT_02: retreive subscriptioable items details from main application
                from("bean:deliveryViewRepository?method=findAll()").split(body(DeliveryView.class)).
                        choice().when().simple("&{operatingExpenseStrategyDeterminator.decideOperatingExpenseStrategy}==com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.DEFAULT_STRATEGY").to("bean:defaultPriceDeterminator").to("bean:publisher")
                        .when().simple("&{operatingExpenseStrategyDeterminator.decideOperatingExpenseStrategy}==com.affaince.subscription.expensedistribution.vo.OperatingExpenseDistributionStrategyType.FORECAST_BASED_STRATEGY")
                        .multicast().to("direct:deliveryExpense").aggregationStrategy(new ProductWiseTotalDeliveryExpenseStrategy())
                        .onCompletion().process(new ProductWiseExpenseDistributionProcessor()).to("bean:publisher");


                from("direct:deliveryExpense").process(new DeliveryExpenseProcessor());
                //from("direct:demandFunction").process(new DemandFunctionCamelProcessor());
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
