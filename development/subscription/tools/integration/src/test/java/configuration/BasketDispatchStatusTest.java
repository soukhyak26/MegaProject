package configuration;

import com.affaince.subscription.integration.command.event.basketdispatch.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.itemreceipt.SubscriptionableItemReceivedEvent;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.test.spring.*;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mandark on 12-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration( classes = {BasketDispatchStatusTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()

public class BasketDispatchStatusTest {
    @EndpointInject(uri = "mock:queue.csv")
    protected MockEndpoint mock;

    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration  {
        @Bean
        @Override
        public RouteBuilder route(){
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file://src/test/resources?noop=true&fileName=BasketDispatchedStatus.in").
                            unmarshal().bindy(BindyType.Csv, BasketDispatchedStatusEvent.class).
                            split(body().tokenize("\n")).streaming()
                            .to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        BasketDispatchedStatusEvent event = new BasketDispatchedStatusEvent();
        event.setBasketId("BSKT_01");
        event.setDispactchStatusCode(01);
        event.setDispatchDate(new SimpleDateFormat("yyyyddMM").parse("20151612"));
        event.setReasonCode(01);

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived(event.toString());
        mock.assertIsSatisfied();
    }

}
