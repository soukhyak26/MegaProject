package com.affaince.subscription.business;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.CreateProvisionCommand;
import com.affaince.subscription.business.configuration.Axon;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by anayonkar on 31/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Axon.class, Application.class})
public class BusinessAccountTest {
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
}
