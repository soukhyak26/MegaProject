package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class LossesDebitedEvent extends DebitedEvent {
    public LossesDebitedEvent() {
    }

    public LossesDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
