package configuration;

import com.affaince.subscription.integration.command.event.dailyquotes.ProductDailyQuoteGeneratedEvent;
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
@ContextConfiguration( classes = {ProductDailyQuoteGenerationTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
public class ProductDailyQuoteGenerationTest {
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
                            marshal().bindy(BindyType.Csv, ProductDailyQuoteGeneratedEvent.class).
                            to("mock:queue.csv");
                }
            };
        }
    }
    @DirtiesContext
    @Test
    public void testPositive() throws Exception {

        ProductDailyQuoteGeneratedEvent event = new ProductDailyQuoteGeneratedEvent();
        event.setProductId("PROD_0345");
        event.setCategoryId("CAT_0234");
        event.setSubCategoryId("SUBCAT_0345");
        event.setQuotedPrice(34.00);
        event.setQuoteDate(new SimpleDateFormat("yyyyddMM").parse("20151612"));

        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived("PRODUCT_ID,CATEGORY_ID,SUBCATEGORY_ID,QUOTED_PRICE,QUOTE_DATE\nPROD_0345,CAT_0234,SUBCAT_0345,34.0,20151612\n");
        template.sendBody(event);
        mock.assertIsSatisfied();

    }

}
