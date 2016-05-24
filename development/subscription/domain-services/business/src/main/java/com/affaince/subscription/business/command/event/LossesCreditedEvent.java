package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class LossesCreditedEvent extends CreditedEvent {
    public LossesCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}