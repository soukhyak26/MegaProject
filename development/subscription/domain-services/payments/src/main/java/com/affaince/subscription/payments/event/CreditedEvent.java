package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 23/8/16.
 */
public abstract class CreditedEvent extends AccountingEvent {
    private double amountToCredit;

    protected CreditedEvent() {
    }

    protected CreditedEvent(String id, double amountToCredit) {
        super(id);
        this.amountToCredit = amountToCredit;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
