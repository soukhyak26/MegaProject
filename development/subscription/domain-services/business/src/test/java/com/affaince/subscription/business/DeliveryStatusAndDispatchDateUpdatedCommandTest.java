package com.affaince.subscription.business;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.business.configuration.Axon;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
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
public class DeliveryStatusAndDispatchDateUpdatedCommandTest {
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
    public void testDeliveryStatusAndDispatchDateUpdatedCommand() {
        DeliveryStatusAndDispatchDateUpdatedCommand deliveryStatusAndDispatchDateUpdatedCommand = new DeliveryStatusAndDispatchDateUpdatedCommand(5.0, 1000.0);
        try {
            commandGateway.executeAsync(deliveryStatusAndDispatchDateUpdatedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
}
