package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class OthersDebitedEvent extends DebitedEvent {
    public OthersDebitedEvent() {
    }

    public OthersDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
