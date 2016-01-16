package com.affaince.notification.configuration;

import com.affaince.notification.events.PaymentProcessedEvent;
import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.configuration.Default;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 16/1/16.
 */

@Configuration
@EnableAutoConfiguration
public class Axon extends ActiveMQConfiguration {

    @Autowired
    CamelContext camelContext;

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                //JacksonDataFormat df = new JacksonDataFormat(PaymentProcessedEvent.class);
                //from("${notification.event.feed.source}").
                from("activemq:queue:Consumer.Notification.VirtualTopic.EventBus").
                        unmarshal().json(JsonLibrary.Jackson).
                        //to("${notification.event.feed.destination}").
                                to("bean:PaymentProcessedEvent").
                        //to("${event.publisher}");
                                to("bean:GenericMailEventPublisher");
            }
        };
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.notification.events.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
        }};
    }
}
