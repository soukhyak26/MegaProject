package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.pricing.forecast.ProductForecastViewBuilder;
import com.affaince.subscription.pricing.product.ProductViewBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;

/**
 * Created by mandar on 18-09-2016.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
//@ContextConfiguration
//@ActiveProfiles("test") // Load applicaton-test.properties in test/resources/config/application-test.properties
@SpringApplicationConfiguration(classes = {CamelRouteTest.class})
//@ContextConfiguration(classes = {Application.class})
//@SpringBootTest(classes = CamelRouteTest.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
//@EnableAutoConfiguration
@ComponentScan
//@Ignore
public class CamelRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:catchRetrievedProducts")
    protected MockEndpoint catchRetrievedProducts;
    @Autowired
    ProductViewBuilder productViewBuilder;
    @Autowired
    ProductForecastViewBuilder productForecastViewBuilder;
    @Autowired
    CamelContext context;
    @Autowired
    ProducerTemplate template;

    @Before
    public void mockEndpoints() throws Exception {
        productViewBuilder.buildProductView();
        // productForecastViewBuilder.buildProductForecast();
        AdviceWithRouteBuilder productsRetriverAdvice = new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                // mock the for testing
                context.stopRoute("productsRetriever");
                replaceFromWith("direct:start");

                interceptSendToEndpoint("jms:queue:forecastQueue?jmsMessageType=Text&concurrentConsumers=10&maxConcurrentConsumers=20")
                        .skipSendToOriginalEndpoint()
                        .to(catchRetrievedProducts);
            }
        };
        context.getRouteDefinition("productsRetriever").adviceWith((ModelCamelContext) context, productsRetriverAdvice);
    }

    @Override
    public boolean isUseAdviceWith() {
        return true;
    }

    @After
    public void tearDown() throws Exception {
        productForecastViewBuilder.clearAll();
        productViewBuilder.clearAll();
    }

    @Test
    public void testProductsRetrieval() throws Exception {

        MockEndpoint mockSolr = getMockEndpoint("mock:catchRetrievedProducts");
        //System.out.println(mockSolr.);
        mockSolr.expectedMessageCount(1000);
        //context.start();
        template.sendBody("direct:start", "hello");
        mockSolr.assertIsSatisfied();
        System.out.println("####IN TEST METHOD#####");
        //context.stop();
    }
}
