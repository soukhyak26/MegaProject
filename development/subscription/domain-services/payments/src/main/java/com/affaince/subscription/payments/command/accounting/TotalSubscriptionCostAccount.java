package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
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
