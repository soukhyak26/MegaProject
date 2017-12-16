package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivableCostAccountDebitedEvent extends DebitedEvent {
    public TotalReceivableCostAccountDebitedEvent() {
    }

    public TotalReceivableCostAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
