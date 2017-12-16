package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BenefitCreditedEvent extends CreditedEvent {
    public BenefitCreditedEvent() {
    }

    public BenefitCreditedEvent(Integer year, double amountToCredit) {
        super(year, amountToCredit);
    }
}
