package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

public class TotalReceivableCostAccount extends Account {
    private String subscriptionId;
    private double rewardPoints;
    public TotalReceivableCostAccount(String subscriptionId,double totalReceivableCost,LocalDate costChangeDate) {
        super(totalReceivableCost,costChangeDate);
        this.subscriptionId=subscriptionId;
    }
    public void addToRewardPoints( double rewardPoints){
        this.rewardPoints +=rewardPoints;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
