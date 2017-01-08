package configuration;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by mandark on 12-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(classes = {ProductStatusFeedTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
@Ignore
public class ProductStatusFeedTest {
    @EndpointInject(uri = "mock:queue.csv")
    protected MockEndpoint mock;

    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        @Override
        public RouteBuilder route() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file://src/test/resources?noop=true&fileName=productStatusFeed.in").
                            unmarshal().bindy(BindyType.Csv, ProductStatusReceivedEvent.class).
                            split(body().tokenize("\n")).streaming().
                            to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        ProductStatusReceivedEvent event1 = new ProductStatusReceivedEvent();
        event1.setCategoryId("cat0");
        event1.setSubCategoryId("subcat0");
        event1.setProductId("0bdd486a-cacc-37ac-8388-aebf1d7c0d66");
        event1.setCurrentPurchasePricePerUnit(257.00);
        event1.setCurrentMRP(444);
        event1.setCurrentStockInUnits(500);
        event1.setCurrentPriceDate(SysDate.now());

        ProductStatusReceivedEvent event2 = new ProductStatusReceivedEvent();
        event2.setCategoryId("cat0");
        event2.setSubCategoryId("subcat0");
        event2.setProductId("0bdd486a-cacc-37ac-8388-aebf1d7c0d66");
        event2.setCurrentPurchasePricePerUnit(257.00);
        event2.setCurrentMRP(444);
        event2.setCurrentStockInUnits(500);
        event2.setCurrentPriceDate(SysDate.now());

        ProductStatusReceivedEvent [] events = new ProductStatusReceivedEvent[2];
        events[0] = event1;
        events[1] = event2;

        System.out.println("%%%%%% I AM PRINTING%%%%%%%%%%%%%%%%%%%%%%%%%");
       // mock.expectedMessageCount(2);
        mock.expectedBodiesReceived(events);
        mock.assertIsSatisfied();
    }

}
