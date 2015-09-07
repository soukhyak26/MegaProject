package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.event.GenericEventPublisher;
import com.affaince.subscription.integration.command.event.basketdispatch.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.itemreceipt.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.event.shoppingitemreceipt.ShoppingItemReceivedEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
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


    public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";
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

                from("file:D://abc?fileName=subscriptionableItemsFeed.in").
                        unmarshal().bindy(BindyType.Csv, SubscriptionableItemReceivedEvent.class).
                        split().body().
                        to("bean:publisher");

                JacksonDataFormat df = new JacksonDataFormat(objectMapper(), BasketDispatchedStatusEvent.class);
                from("file:D://abc?fileName=BasketDispatchedStatus.in").
                        unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                        split().body().
                        to("bean:publisher");

                from("file:D://abc?fileName=ShoppingItemsForRegistration.in").
                        unmarshal().bindy(BindyType.Csv, ShoppingItemReceivedEvent.class).
                        split().body().
                        to("bean:publisher");

                from("bean:NotificationEvent").marshal("json").to("restlet:/notification?restletMethod=POST");

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
