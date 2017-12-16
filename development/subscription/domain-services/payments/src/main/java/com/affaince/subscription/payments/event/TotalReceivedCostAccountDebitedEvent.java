package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivedCostAccountDebitedEvent extends DebitedEvent {
    public TotalReceivedCostAccountDebitedEvent() {
    }

    public TotalReceivedCostAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
