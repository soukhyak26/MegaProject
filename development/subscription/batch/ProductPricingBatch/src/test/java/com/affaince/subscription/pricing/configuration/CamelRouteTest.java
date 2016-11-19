package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.pricing.Application;
import com.affaince.subscription.pricing.build.ProductConfigurationViewBuilder;
import com.affaince.subscription.pricing.build.ProductForecastViewBuilder;
import com.affaince.subscription.pricing.build.ProductViewBuilder;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by mandar on 18-09-2016.
 */
@RunWith(CamelSpringJUnit4ClassRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@MockEndpoints()
@WebAppConfiguration
@ComponentScan
@Ignore
public class CamelRouteTest extends CamelTestSupport {

    @EndpointInject(uri = "mock:catchRetrievedProducts")
    protected MockEndpoint catchRetrievedProducts;
    @Autowired
    ProductViewBuilder productViewBuilder;
    @Autowired
    ProductForecastViewBuilder productForecastViewBuilder;
    @Autowired
    ProductConfigurationViewBuilder productConfigurationViewBuilder;
    @Autowired
    CamelContext context;
    @Autowired
    ProducerTemplate template;

    @Mock
    ProductConfigurationViewRepository productConfigurationViewRepository;

    @Before
    public void mockEndpoints() throws Exception {
        productViewBuilder.buildProductView();
        productForecastViewBuilder.buildProductForecast();
        productConfigurationViewBuilder.buildProductConfigurationView();
        AdviceWithRouteBuilder productsRetriverAdvice = new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                // mock the for testing
                context.stopRoute("productsRetriever");
                replaceFromWith("direct:start");
                interceptSendToEndpoint("{{subscription.pricing.poston}}")
                        .skipSendToOriginalEndpoint()
                        .to("mock:catchRetrievedProducts");
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
        productConfigurationViewBuilder.clearAll();
    }

    @Test
    public void testProductsRetrieval() throws Exception {
        MockEndpoint mockSolr = getMockEndpoint("mock:catchRetrievedProducts");
        mockSolr.expectedMessageCount(1000);
        template.sendBody("direct:start", "hello");
        mockSolr.assertIsSatisfied();
        System.out.println("####IN TEST METHOD#####");
    }

}
