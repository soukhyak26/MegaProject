package configuration;

import com.affaince.subscription.integration.command.event.shoppingitemreceipt.ProductReceivedForRegistrationEvent;
import org.apache.camel.EndpointInject;
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
 * Created by mandark on 12-09-2015.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration( classes = {ProductRegistrationReceiptTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
public class ProductRegistrationReceiptTest {
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
                    from("file://src/test/resources?noop=true&fileName=ProductsForRegistration.in").
                            unmarshal().bindy(BindyType.Csv, ProductReceivedForRegistrationEvent.class).
                            split(body().tokenize("\n")).streaming()
                            .to("mock:queue.csv");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void testPositive() throws Exception {
        ProductReceivedForRegistrationEvent event = new ProductReceivedForRegistrationEvent();
        event.setCategoryId("1111");
        event.setSubCategoryId("23");
        event.setProductId("TOOTHPSTCOLGT");
        event.setProductName("TOOTHPSTCOLGT");
        mock.expectedMessageCount(1);
        mock.expectedBodiesReceived(event.toString());
        mock.assertIsSatisfied();
    }

}
