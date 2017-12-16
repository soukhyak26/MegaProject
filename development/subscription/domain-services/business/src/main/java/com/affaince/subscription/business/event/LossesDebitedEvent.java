package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class LossesDebitedEvent extends DebitedEvent {
    public LossesDebitedEvent() {
    }

    public LossesDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
