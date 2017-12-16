package com.affaince.subscription.business.event;

public class RevenueCreditedEvent extends CreditedEvent {
    private String contributorId;
    public RevenueCreditedEvent() {
    }

    public RevenueCreditedEvent(Integer businessAccountId,String contributorId,double amountToCredit) {
        super(businessAccountId, amountToCredit);
        this.contributorId=contributorId;
    }

    public String getContributorId() {
        return contributorId;
    }
}
