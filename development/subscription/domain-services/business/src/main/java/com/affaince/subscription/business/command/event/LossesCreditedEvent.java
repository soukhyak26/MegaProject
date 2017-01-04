package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class LossesCreditedEvent extends CreditedEvent {
    public LossesCreditedEvent() {
    }

    public LossesCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
