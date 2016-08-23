package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 23/8/16.
 */
public abstract class DebitedEvent extends AccountingEvent {
    private double amountToDebit;

    protected DebitedEvent() {
    }

    protected DebitedEvent(String id, double amountToDebit) {
        super(id);
        this.amountToDebit = amountToDebit;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}
