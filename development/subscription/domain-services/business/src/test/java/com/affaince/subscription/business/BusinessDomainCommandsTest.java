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
import org.junit.Ignore;
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
@Ignore
public class BusinessDomainCommandsTest {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    @Autowired
    private BusinessAccountViewRepository businessAccountViewRepository;

    @Test
    public void testCreateProvisionForPurchaseCostCommand() {
        CreateProvisionForPurchaseCostCommand createProvisionCommand = new CreateProvisionForPurchaseCostCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForBenefitsCommand() {
        CreateProvisionForBenefitsCommand createProvisionCommand = new CreateProvisionForBenefitsCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForLossesCommand() {
        CreateProvisionForLossesCommand createProvisionCommand = new CreateProvisionForLossesCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForNodalCommand() {
        CreateProvisionForNodalCommand createProvisionCommand = new CreateProvisionForNodalCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForOthersCommand() {
        CreateProvisionForOthersCommand createProvisionCommand = new CreateProvisionForOthersCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForSubscriptionSpecificExpensesCommand() {
        CreateProvisionForSubscriptionSpecificExpensesCommand createProvisionCommand = new CreateProvisionForSubscriptionSpecificExpensesCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForCommonExpensesCommand() {
        CreateProvisionForCommonExpensesCommand createProvisionCommand = new CreateProvisionForCommonExpensesCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testCreateProvisionForTaxesCommand() {
        CreateProvisionForTaxesCommand createProvisionCommand = new CreateProvisionForTaxesCommand(
                Integer.valueOf(SysDate.now().getYear()),
                SysDate.now(),
                new LocalDate(2017,12,31),
                10000.0);
        try {
            commandGateway.sendAndWait(createProvisionCommand);
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
        //testCreateProvisionCommand();
    }

    @Test
    public void testCommonExpenseReceivedCommand() {
        OperatingExpenseReceivedCommand commonOperatingExpense = new OperatingExpenseReceivedCommand(ExpenseType.COMMON_EXPENSE, 1000.0);
        try {
            commandGateway.sendAndWait(commonOperatingExpense);
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
            commandGateway.sendAndWait(deliveryStatusAndDispatchDateUpdatedCommand);
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
            commandGateway.sendAndWait(paymentProcessedCommand);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

    @Test
    public void testProductStatusReceivedCommand() {
        ChangePurchaseProvisionPerProductCommand changePurchaseProvisionPerProductCommand = new ChangePurchaseProvisionPerProductCommand(SysDate.now().getYear(),1000.0);
        try {
            commandGateway.sendAndWait(changePurchaseProvisionPerProductCommand);
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
            commandGateway.sendAndWait(subscriptionActivatedCommand);
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
            commandGateway.sendAndWait(subscriptionSpecificOperatingExpense);
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }

}
