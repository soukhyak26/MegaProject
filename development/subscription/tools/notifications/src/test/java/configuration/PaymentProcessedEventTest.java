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
//@MockEndpoints()
//@Ignore
public class PaymentProcessedEventTest {

    /*@EndpointInject(uri = "mock:queue.csv")
    protected MockEndpoint mock;*/
    //protected Endpoint mock;
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
                    /*from("direct:start").
                            marshal().bindy(, PaymentProcessedEvent.class).
                            to("mock:queue.csv");*/
                    from("direct:start").
                            marshal().json(JsonLibrary.Jackson).
                            //to("jms:notification.queue");
                            //to("jms:notification.queue");
                            //to("activemq:topic:VirtualTopic.EventBus");
                            to("rabbitmq://localhost/notification.exchange?routingKey=com.affaince.notification.events.PaymentProcessedEvent&durable=true&declare=false");
                                    //to("rabbitmq://localhost/notification.exchange?durable=true&declare=false");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
       /* ProductStatusReceivedEvent event = new ProductStatusReceivedEvent();
        event.setCategoryId("CATEGORY1");
        event.setSubCategoryId("23");
        event.setProductId("TOOTHPSTCOLGT");
        event.setCurrentPurchasePricePerUnit(47.00);
        event.setCurrentMRP(74.50);
        event.setCurrentStockInUnits(23456);
        event.setCurrentPriceDate(LocalDate.now());
        mock.expectedMessageCount(1);*/
        //PaymentProcessedEvent event = new PaymentProcessedEvent("111", "007", 1000, null);
        PaymentProcessedEvent event = new PaymentProcessedEvent();
        event.setPaymentAmount(1000);
        //event.setPaymentDate(new LocalDate());
        event.setSubscriberId("111");
        event.setSubscriptionId("007");
        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(event);
        //System.out.println("\t\t\t\t**********\n\t\t\t\tExpected is\n\t\t\t\t********** : " + expected);
        //mock.expectedBodiesReceived(expected);
        //mock.expectedBodiesReceived(event.toString());
        //mock.expectedBodyReceived();
        template.sendBody(event);
        //mock.assertIsSatisfied();
    }
}
