package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.event.GenericEventPublisher;
import com.affaince.subscription.integration.command.event.basketdispatch.request.BasketDispatchRequestGeneratedEvent;
import com.affaince.subscription.integration.command.event.basketdispatch.status.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.dailyquotes.SubscriptionableItemDailyQuoteGeneratedEvent;
import com.affaince.subscription.integration.command.event.forecast.ProductForecastReceivedEvent;
import com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent;
import com.affaince.subscription.integration.command.event.shoppingitemreceipt.ProductReceivedForRegistrationEvent;
import com.affaince.subscription.integration.command.event.stockdemand.SubscriptionableItemStockDemandGeneratedEvent;
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
                //INT_02: retreive subscriptioable items details from main application
                from("${subscriptionableitems.feed.source}").
                        unmarshal().bindy(BindyType.Csv, ProductStatusReceivedEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${subscriptionableitems.feed.destination}");

                JacksonDataFormat df = new JacksonDataFormat(objectMapper(), BasketDispatchedStatusEvent.class);

                //INT_05: update status of targetted dispatches every day
                from("${basket.dispatch.status.source}").
                        unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${basket.dispatch.status.destination}");

                //INT_01: retreive shopping items for registration
                from("${shoppingitems.registration.source}").
                        unmarshal().bindy(BindyType.Csv, ProductReceivedForRegistrationEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${shoppingitems.registration.destination}");

                //INT_XX: retreive forecasts for every shopping item that has been registered
                from("${shoppingitems.forecast.source}").
                        unmarshal().bindy(BindyType.Csv, ProductForecastReceivedEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${shoppingitems.forecast.destination}");

                //INT_03: generate stock demand based on available subscripttions
                from("${stock.demand.source}").
                        marshal().bindy(BindyType.Csv, SubscriptionableItemStockDemandGeneratedEvent.class).
                        to("${stock.demand.destination}");
                //INT_04: generate basket dispatch requests to main application
                from("${dispatch.request.source}").
                        marshal().bindy(BindyType.Csv, BasketDispatchRequestGeneratedEvent.class).
                        to("${dispatch.request.destination}");

                //INT_06: generate daly quote for each subscriptionable item to main application
                from("${generate.quote.source}").
                        marshal().bindy(BindyType.Csv, SubscriptionableItemDailyQuoteGeneratedEvent.class).
                        to("${generate.qoute.destination}");

                //from("bean:NotificationEvent").marshal("json").to("restlet:/notification?restletMethod=POST");

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
