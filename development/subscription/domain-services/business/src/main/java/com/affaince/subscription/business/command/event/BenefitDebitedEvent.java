package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BenefitDebitedEvent {
    private String businessAccountId;
    private double amountToDebit;

    public BenefitDebitedEvent(String businessAccountId, double amountToDebit) {
        this.businessAccountId = businessAccountId;
        this.amountToDebit = amountToDebit;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}
