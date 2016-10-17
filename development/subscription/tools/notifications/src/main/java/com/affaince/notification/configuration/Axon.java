package com.affaince.notification.configuration;

import com.affaince.notification.events.PaymentProcessedEvent;
import com.affaince.notification.publisher.GenericMailEventPublisher;
import com.affaince.subscription.configuration.Default;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
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
public class Axon extends Default {

    @Autowired
    CamelContext camelContext;


    @Bean
    public GenericMailEventPublisher GenericMailEventPublisher() {
        return new GenericMailEventPublisher();
    }

    @Bean
    public PaymentProcessedEvent PaymentProcessedEvent() {
        return new PaymentProcessedEvent();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                JacksonDataFormat df = new JacksonDataFormat(PaymentProcessedEvent.class);
                from("rabbitmq://localhost/notification.exchange?queue=notification.queue&durable=true&declare=false").
                                unmarshal(df).
                                to("bean:GenericMailEventPublisher");
            }
        };
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.notification.events.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
        }};
    }
}