package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class OthersDebitedEvent extends DebitedEvent {
    public OthersDebitedEvent() {
    }

    public OthersDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
