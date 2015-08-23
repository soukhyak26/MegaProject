package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.event.basketdispatch.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.GenericEventPublisher;
import com.affaince.subscription.integration.command.event.itemreceipt.SubscriptionableItemReceivedEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends Default {


    @Autowired
    CamelContext camelContext;
    public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }


    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() {
                from("file:D://abc?fileName=subscriptionableItemsFeed.in").
                        unmarshal().bindy(BindyType.Csv, SubscriptionableItemReceivedEvent.class).
                        split().body().
                        to("bean:publisher");

                from("file:D://abc?fileName=BasketDispatchedStatus.in").
                        unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                        split().body().
                        to("bean:publisher");


            }
        };
    }

/*
    @Bean
    public RouteBuilder route2() {
        return new RouteBuilder() {
            public void configure() {
                from("file:D://abc//BasketDispatchedStatus.in").
                        unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                        split().body().
                        to("bean:publisher");

            }
        };
    }
*/

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
