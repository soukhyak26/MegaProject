package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BenefitCreditedEvent {
    private String businessAccountId;
    private double amountToCredit;

    public BenefitCreditedEvent(String businessAccountId, double amountToCredit) {
        this.businessAccountId = businessAccountId;
        this.amountToCredit = amountToCredit;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
