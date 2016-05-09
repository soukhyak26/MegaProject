package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class CreditedEvent extends Event {
    private double amountToCredit;

    protected CreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId);
        this.amountToCredit = amountToCredit;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
