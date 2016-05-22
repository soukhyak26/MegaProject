package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class DebitedEvent extends AccountingEvent {
    private double amountToDebit;

    protected DebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId);
        this.amountToDebit = amountToDebit;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}
