package configuration;

//import com.affaince.notification.events.PaymentProcessedEvent;

import com.affaince.notification.events.PaymentProcessedEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by anayonkar on 16/1/16.
 */

@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(classes = {PaymentProcessedEventTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Ignore
public class PaymentProcessedEventTest {

    @Produce(uri = "direct:start")
    protected ProducerTemplate template;


    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        @Override
        public RouteBuilder route() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start").
                            marshal().json(JsonLibrary.Jackson).
                            to("rabbitmq://localhost/notification.exchange?routingKey=com.affaince.notification.events.PaymentProcessedEvent&durable=true&declare=false");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        PaymentProcessedEvent event = new PaymentProcessedEvent();
        event.setPaymentAmount(1000);
        event.setSubscriberId("111");
        event.setSubscriptionId("007");
        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(event);
        template.sendBody(event);
    }
}
