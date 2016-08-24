package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class PaymentReceivedAccountDebitedEvent extends DebitedEvent {
    public PaymentReceivedAccountDebitedEvent() {
    }

    public PaymentReceivedAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
