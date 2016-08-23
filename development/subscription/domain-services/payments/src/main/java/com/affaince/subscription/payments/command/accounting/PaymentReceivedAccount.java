package com.affaince.subscription.payments.command.accounting;

/**
 * Created by anayonkar on 23/8/16.
 */
public class PaymentReceivedAccount extends Account {

    public PaymentReceivedAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {

    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {

    }
}
