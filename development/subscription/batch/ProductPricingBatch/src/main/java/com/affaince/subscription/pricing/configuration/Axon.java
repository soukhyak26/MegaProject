package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.pricing.processor.camel.CoefficientAggregationStrategy;
import com.affaince.subscription.pricing.processor.camel.CostFunctionCamelProcessor;
import com.affaince.subscription.pricing.processor.camel.DemandFunctionCamelProcessor;
import com.affaince.subscription.pricing.processor.camel.PriceDeterminationCamelProcessor;
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
    CamelContext camelContext;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }


    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                //INT_02: retreive subscriptioable items details from main application
                from("bean:productViewRepository?method=findAll()").multicast().parallelProcessing().to("direct:costFunction", "direct:demandFunction").aggregationStrategy(new CoefficientAggregationStrategy()).onCompletion().process(new PriceDeterminationCamelProcessor());


                from("direct:costFunction").process(new CostFunctionCamelProcessor());
                from("direct:demandFunction").process(new DemandFunctionCamelProcessor());
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
