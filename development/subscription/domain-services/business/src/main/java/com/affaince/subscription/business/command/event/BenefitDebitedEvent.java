package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BenefitDebitedEvent extends DebitedEvent {
    public BenefitDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}