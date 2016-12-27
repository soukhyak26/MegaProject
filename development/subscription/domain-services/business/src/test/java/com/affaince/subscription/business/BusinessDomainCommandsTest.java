package com.affaince.subscription.business;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.*;
import com.affaince.subscription.business.configuration.Axon;
import com.affaince.subscription.business.query.repository.BusinessAccountViewRepository;
import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.date.SysDate;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by anayonkar on 28/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Axon.class, Application.class})
public class BusinessDomainCommandsTest {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    private BusinessAccountViewRepository businessAccountViewRepository;

    private void testCreateProvisionCommand() {
        CreateProvisionCommand createProvisionCommand = new CreateProvisionCommand(10000.0,
                50000.0,
                25000.0,
                20000.0,
                10000.0,
                10000.0,
                50000.0,
                SysDate.now());
        try {
            commandGateway.executeAsync(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Before
    public void init() {
        Assert.assertNotNull(commandGateway);
        Assert.assertNotNull(businessAccountViewRepository);
        testCreateProvisionCommand();
    }

    @Test
    public void testCommonExpenseReceivedCommand() {
        OperatingExpenseReceivedCommand commonOperatingExpense = new OperatingExpenseReceivedCommand(ExpenseType.COMMON_EXPENSE, 1000.0);
        try {
            commandGateway.executeAsync(commonOperatingExpense);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
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

    @Test
    public void testPaymentProcessedCommand() {
        PaymentProcessedCommand paymentProcessedCommand = new PaymentProcessedCommand(1000.0);
        try {
            commandGateway.executeAsync(paymentProcessedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testProductStatusReceivedCommand() {
        ProductStatusReceivedCommand productStatusReceivedCommand = new ProductStatusReceivedCommand(1000.0, 10000);
        try {
            commandGateway.executeAsync(productStatusReceivedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSubscriptionActivatedCommand() {
        SubscriptionActivatedCommand subscriptionActivatedCommand = new SubscriptionActivatedCommand(10.0);
        try {
            commandGateway.executeAsync(subscriptionActivatedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testSubscriptionSpecificExpenseReceivedCommand() {
        OperatingExpenseReceivedCommand subscriptionSpecificOperatingExpense = new OperatingExpenseReceivedCommand(ExpenseType.SUBSCRIPTION_SPECIFIC_EXPENSE, 1000.0);
        try {
            commandGateway.executeAsync(subscriptionSpecificOperatingExpense);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

}
