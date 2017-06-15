package com.affaince.subscription.integration.configuation;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.integration.command.event.basketdispatch.status.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
                from("file:D://abc?fileName=productStatusFeed.in&preMove=inprogress&move=backup/${date:now:yyyyMMdd}/${file:name}&idempotent=true").
                        unmarshal().bindy(BindyType.Csv, ProductStatusReceivedEvent.class).
                        split(body())
                        .to("bean:publisher");

                JacksonDataFormat df = new JacksonDataFormat(new ObjectMapper(), BasketDispatchedStatusEvent.class);

               /* //INT_05: update status of targetted dispatches every day
                from("${basket.dispatch.status.source}").
                        unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${basket.dispatch.status.destination}");

                //INT_01: retreive products for registration
                from("${product.registration.source}").
                        unmarshal().bindy(BindyType.Csv, ProductRegisteredEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${product.registration.destination}");

                //INT_XX: retreive forecasts for every product that has been registered
                from("${product.forecast.source}").
                        unmarshal().bindy(BindyType.Csv, ProductForecastReceivedEvent.class).
                        split(body().tokenize("\n")).streaming().
                        to("${product.forecast.destination}");

                //INT_03: generate stock demand based on available subscripttions
                from("${product.stock.demand.source}").
                        marshal().bindy(BindyType.Csv, ProductStockDemandGeneratedEvent.class).
                        to("${product.stock.demand.destination}");
                //INT_04: generate basket dispatch requests to main application
                from("${basket.dispatch.request.source}").
                        marshal().bindy(BindyType.Csv, BasketDispatchRequestGeneratedEvent.class).
                        to("${basket.dispatch.request.destination}");

                //INT_06: generate daly quote for each subscriptionable item to main application
                from("${product.generate.quote.source}").
                        marshal().bindy(BindyType.Csv, ProductDailyQuoteGeneratedEvent.class).
                        to("${product.generate.qoute.destination}");

                //INT_07: Receive payment receipt intimation from main application
                from("${payment.receipt.info.source}").
                        marshal().bindy(BindyType.Csv, PaymentReceivedEvent.class).
                        to("${payment.receipt.info.destination}");

                //from("bean:NotificationEvent").marshal("json").to("restlet:/notification?restletMethod=POST");

                //INT_07: Receive operating expense intimation from main application
                from("${operatingexpense.info.source}").
                        marshal().bindy(BindyType.Csv, OperatingExpenseReceivedEvent.class).
                        to("${operatingexpense.info.destination}");
*/
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
        return new HashMap<>();
    }
}
