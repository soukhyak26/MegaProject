package configuration;

import com.affaince.subscription.integration.command.event.stockdemand.SubscriptionableItemStockDemandGeneratedEvent;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.dataformat.BindyType;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by mandark on 17-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(classes = {SubscriptionableItemStockDemandGenerationTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()

public class SubscriptionableItemStockDemandGenerationTest {
    @EndpointInject(uri = "mock:queue.csv")
    protected MockEndpoint mock;
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
                            marshal().bindy(BindyType.Csv, SubscriptionableItemStockDemandGeneratedEvent.class).
                            to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        SubscriptionableItemStockDemandGeneratedEvent event = new SubscriptionableItemStockDemandGeneratedEvent();
        event.setProductId("PROD_0345");
        event.setCategoryId("CAT_0234");
        event.setCategoryName("CAT_0234");
        event.setSubCategoryId("SUBCAT_0345");
        event.setSubCategoryName("SUBCAT_0345");
        event.setItemId("ITEM_0123");
        event.setCurrentStockDemandInUnits(2375);

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("ITEM_ID,CATEGORY_ID,CATEGORY_NAME,SUBCATEGORY_ID,SUBCATEGORY_NAME,PRODUCT_ID,CURRENT_STOCK_DEMAND\nITEM_0123,CAT_0234,CAT_0234,SUBCAT_0345,SUBCAT_0345,PROD_0345,2375\n");
        template.sendBody(event);
        mock.assertIsSatisfied();

    }

}
