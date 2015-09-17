package configuration;

import com.affaince.subscription.integration.command.event.itemreceipt.SubscriptionableItemReceivedEvent;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.joda.time.LocalDate;
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
@ContextConfiguration( classes = {SubscriptionableItemReceiptTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
public class SubscriptionableItemReceiptTest {
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
                    from("file://src/test/resources?noop=true&fileName=subscriptionableItemsFeed.in").
                            unmarshal().bindy(BindyType.Csv, SubscriptionableItemReceivedEvent.class).
                            split(body().tokenize("\n")).streaming()
                            .to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        SubscriptionableItemReceivedEvent event = new SubscriptionableItemReceivedEvent();
        event.setItemId("31");
        event.setBatchId("19072015");
        event.setCategoryId("1111");
        event.setCategoryName("CATEGORY1");
        event.setSubCategoryId("23");
        event.setSubCategoryName("SUBCATEGORY23");
        event.setProductId("TOOTHPSTCOLGT");
        event.setCurrentPurchasePricePerUnit(47.00);
        event.setCurrentMRP(74.50);
        event.setCurrentStockInUnits(23456);
        event.setCurrentPriceDate(LocalDate.now());
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived(event.toString());
        mock.assertIsSatisfied();
    }

}
