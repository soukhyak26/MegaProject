package configuration;

import com.affaince.subscription.integration.command.event.basketdispatch.BasketDispatchedStatusEvent;
import com.affaince.subscription.integration.command.event.itemreceipt.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.event.shoppingitemreceipt.ShoppingItemReceivedEvent;
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


/**
 * Created by mandark on 12-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration( classes = {ShoppingItemReceiptTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()

public class ShoppingItemReceiptTest {
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
                    from("file://src/test/resources?noop=true&fileName=ShoppingItemsForRegistration.in").
                            unmarshal().bindy(BindyType.Csv, ShoppingItemReceivedEvent.class).
                            split(body().tokenize("\n")).streaming()
                            .to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        ShoppingItemReceivedEvent event = new ShoppingItemReceivedEvent();
        event.setShoppingItemId("31");
        event.setCategoryId("1111");
        event.setCategoryName("CATEGORY1");
        event.setSubCategoryId("23");
        event.setSubCategoryName("SUBCATEGORY23");
        event.setProductId("TOOTHPSTCOLGT");

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived(event.toString());
        mock.assertIsSatisfied();
    }

}
