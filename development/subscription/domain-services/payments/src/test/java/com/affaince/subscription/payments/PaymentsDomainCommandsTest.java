package com.affaince.subscription.payments;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.PaymentReceivedCommand;
import com.affaince.subscription.payments.configuration.Axon;
import com.affaince.subscription.payments.query.repository.SubscriptionPaymentViewRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by anayonkar on 20/10/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Axon.class, Application.class})
public class PaymentsDomainCommandsTest {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    private SubscriptionPaymentViewRepository subscriptionPaymentViewRepository;

    private void testPaymentReceivedCommand() {
        PaymentReceivedCommand paymentReceivedCommand = new PaymentReceivedCommand("subscriber_100", "subscription_100", 1000.0, SysDate.now());
        try {
            commandGateway.executeAsync(paymentReceivedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    //TODO : change to @Test after other tests are written
    public void init() {
        Assert.assertNotNull(commandGateway);
        Assert.assertNotNull(subscriptionPaymentViewRepository);
        testPaymentReceivedCommand();
    }
}
