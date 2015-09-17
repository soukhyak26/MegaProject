package configuration;

import com.affaince.subscription.integration.command.event.basketdispatch.request.BasketDispatchRequestGeneratedEvent;
import com.affaince.subscription.integration.command.event.dailyquotes.SubscriptionableItemDailyQuoteGeneratedEvent;
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

import java.text.SimpleDateFormat;

/**
 * Created by mandark on 17-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration( classes = {SubscriptionableItemDailyQuoteGenerationTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()

public class SubscriptionableItemDailyQuoteGenerationTest {
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
                            marshal().bindy(BindyType.Csv, SubscriptionableItemDailyQuoteGeneratedEvent.class).
                            to("mock:queue.csv");
                }
            };
        }
    }
    @DirtiesContext
    @Test
    public void testPositive() throws Exception {

        SubscriptionableItemDailyQuoteGeneratedEvent event = new SubscriptionableItemDailyQuoteGeneratedEvent();
        event.setProductId("PROD_0345");
        event.setCategoryId("CAT_0234");
        event.setSubCategoryId("SUBCAT_0345");
        event.setItemId("ITEM_0123");
        event.setQuotedPrice(34.00);
        event.setQuoteDate(new SimpleDateFormat("yyyyddMM").parse("20151612"));

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("ITEM_ID,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_ID,QUOTED_PRICE,QUOTE_DATE\nITEM_0123,CAT_0234,SUBCAT_0345,PROD_0345,34.0,20151612\n");
        template.sendBody(event);
        mock.assertIsSatisfied();

    }

}
