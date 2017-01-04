package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class OthersCreditedEvent extends CreditedEvent {
    public OthersCreditedEvent() {
    }

    public OthersCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
