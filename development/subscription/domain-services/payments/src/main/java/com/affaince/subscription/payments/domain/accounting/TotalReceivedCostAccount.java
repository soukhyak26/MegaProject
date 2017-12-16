package com.affaince.subscription.payments.domain.accounting;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 28/8/16.
 */
public class    TotalReceivedCostAccount extends Account {
    private String subscriptionId;
    public TotalReceivedCostAccount(String subscriptionId,double totalReceivableCost,LocalDate costChangeDate) {
        super(totalReceivableCost,costChangeDate);
        this.subscriptionId=subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
