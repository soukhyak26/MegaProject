package com.affaince.subscription.business;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.command.event.CreateProvisionEvent;
import com.affaince.subscription.business.configuration.Axon;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by anayonkar on 25/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Axon.class, Application.class})
@Ignore
//@RunWith(CamelSpringJUnit4ClassRunner.class)
//@BootstrapWith(CamelTestContextBootstrapper.class)
/*@ContextConfiguration(classes = {BusinessAccountProvisionControllerTest.ContextConfig.class},
        loader = CamelSpringDelegatingTestContextLoader.class)*/
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@Ignore
//@Component
public class BusinessAccountProvisionControllerTest {
    @Autowired
    private SubscriptionCommandGateway commandGateway;
    @Autowired
    private BusinessAccountViewRepository businessAccountViewRepository;
    @Test
    public void testInit() {
        Assert.assertNotNull(commandGateway);
        Assert.assertNotNull(businessAccountViewRepository);
    }
    @Test
    public void testProvision() {
        CreateProvisionCommand command = new CreateProvisionCommand(10000.0,
                50000.0,
                25000.0,
                20000.0,
                10000.0,
                10000.0,
                50000.0,
                LocalDate.parse("2016-04-29"));
        try {
            commandGateway.executeAsync(command);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    /*@Produce(uri = "direct:start")
    private ProducerTemplate template;

    @Configuration
    public static class ContextConfig extends SingleRouteCamelConfiguration {
        @Bean
        @Override
        public RouteBuilder route() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:start").
                            marshal().json(JsonLibrary.Jackson).
                            to("http://localhost:8082/businessacount/setProvision");
                }
            };
        }
    }

    @DirtiesContext
    @Test
    public void test() {
        *//*CreateProvisionCommand command = new CreateProvisionCommand(10000.0,
                50000.0,
                25000.0,
                20000.0,
                10000.0,
                10000.0,
                50000.0,
                LocalDate.parse("2016-04-29"));*//*
        CreateProvisionEvent event = new CreateProvisionEvent("ba_id",
                50000.0,
                25000.0,
                20000.0,
                10000.0,
                10000.0,
                50000.0,
                20000.0,
                LocalDate.parse("2016-04-29"));
        template.sendBody(event);
    }*/
}
