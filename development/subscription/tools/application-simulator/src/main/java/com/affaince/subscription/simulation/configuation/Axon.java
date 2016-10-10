package com.affaince.subscription.simulation.configuation;

import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.simulation.command.event.GenericEventPublisher;
import com.affaince.subscription.simulation.command.event.basketdispatch.BasketDispatchedStatusEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends RabbitMQConfiguration {


    private static final String CAMEL_URL_MAPPING = "/camel/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";
    @Autowired
    CamelContext camelContext;

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }


    @Bean
    public RouteBuilder simulatorRoutes() {
        return new RouteBuilder() {
            public void configure() {
                restConfiguration().component("servlet").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true").contextPath("application-simulator/rest").port(8100);

                BindyCsvDataFormat bindy = new BindyCsvDataFormat(com.affaince.subscription.simulation.command.event.basketdispatch.BasketDispatchedStatusEvent.class);

                rest("/basketdispatchstatus").description("Basket Dispatch Status").outTypeList(BasketDispatchedStatusEvent.class).to("bean:basketDispatchStatusService?method=getDispatchedBaskets()");

                from("bean:basketDispatchStatusService?method=getDispatchedBaskets()").marshal(bindy).to("file://abc//BasketDispatchedStatus.out");
            }
        };
    }

    @Bean
    public ServletRegistrationBean camelServletregistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), true, CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }
}
