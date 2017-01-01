package com.affaince.subscription.business.command.event;

import org.joda.time.YearMonth;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class CreditedEvent extends AccountingEvent {
    private double amountToCredit;

    public CreditedEvent() {
    }

    protected CreditedEvent(String year, double amountToCredit) {
        super(year);
        this.amountToCredit = amountToCredit;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
