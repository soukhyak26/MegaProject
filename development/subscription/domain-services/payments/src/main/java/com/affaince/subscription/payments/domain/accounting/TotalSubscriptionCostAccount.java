package com.affaince.subscription.payments.domain.accounting;

import org.joda.time.LocalDate;


public class TotalSubscriptionCostAccount extends Account {
    private String subscriptionId;
    public TotalSubscriptionCostAccount(String subscriptionId,double totalReceivableCost,LocalDate costChangeDate) {
        super(totalReceivableCost,costChangeDate);
        this.subscriptionId=subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
