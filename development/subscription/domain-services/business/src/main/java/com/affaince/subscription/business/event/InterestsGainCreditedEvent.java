package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class InterestsGainCreditedEvent extends CreditedEvent {
    public InterestsGainCreditedEvent() {
    }

    public InterestsGainCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
