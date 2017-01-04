package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class RevenueCreditedEvent extends CreditedEvent {
    public RevenueCreditedEvent() {
    }

    public RevenueCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
