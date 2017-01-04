package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class NodalAccountDebitedEvent extends DebitedEvent {
    public NodalAccountDebitedEvent() {
    }

    public NodalAccountDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
