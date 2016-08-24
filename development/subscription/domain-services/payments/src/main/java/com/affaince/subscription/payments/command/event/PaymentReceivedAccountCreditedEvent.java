package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class PaymentReceivedAccountCreditedEvent extends CreditedEvent {
    public PaymentReceivedAccountCreditedEvent() {
    }

    public PaymentReceivedAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
