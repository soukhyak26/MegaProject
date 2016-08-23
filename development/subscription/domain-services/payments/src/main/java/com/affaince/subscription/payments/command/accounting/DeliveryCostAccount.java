package com.affaince.subscription.payments.command.accounting;

/**
 * Created by anayonkar on 23/8/16.
 */
public class DeliveryCostAccount extends Account {

    public DeliveryCostAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {

    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {

    }
}
