package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalSubscriptionCostAccountDebitedEvent extends DebitedEvent {
    public TotalSubscriptionCostAccountDebitedEvent() {
    }

    public TotalSubscriptionCostAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
