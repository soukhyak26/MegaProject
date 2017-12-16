package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class CreditedEvent extends AccountingEvent {
    private double amountToCredit;

    public CreditedEvent() {
    }

    protected CreditedEvent(Integer year, double amountToCredit) {
        super(year);
        this.amountToCredit = amountToCredit;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
