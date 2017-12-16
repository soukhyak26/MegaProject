package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class DebitedEvent extends AccountingEvent {
    private double amountToDebit;

    protected DebitedEvent() {
    }

    protected DebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId);
        this.amountToDebit = amountToDebit;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}