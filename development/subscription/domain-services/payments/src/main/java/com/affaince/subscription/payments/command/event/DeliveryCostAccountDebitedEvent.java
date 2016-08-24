package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class DeliveryCostAccountDebitedEvent extends DebitedEvent {
    public DeliveryCostAccountDebitedEvent() {
    }

    public DeliveryCostAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
