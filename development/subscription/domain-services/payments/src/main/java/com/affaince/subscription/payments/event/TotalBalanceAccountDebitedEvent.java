package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class TotalBalanceAccountDebitedEvent extends DebitedEvent {
    public TotalBalanceAccountDebitedEvent() {
    }

    public TotalBalanceAccountDebitedEvent(String id, double amountToDebit) {
        super(id, amountToDebit);
    }
}
