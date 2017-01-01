package com.affaince.subscription.business.command.event;

import org.joda.time.YearMonth;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BenefitCreditedEvent extends CreditedEvent {
    public BenefitCreditedEvent() {
    }

    public BenefitCreditedEvent(String year, double amountToCredit) {
        super(year, amountToCredit);
    }
}
