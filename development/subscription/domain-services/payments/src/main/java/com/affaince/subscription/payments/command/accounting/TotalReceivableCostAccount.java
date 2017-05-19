package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivableCostAccount extends Account {
    private String subscriptionId;
    public TotalReceivableCostAccount(String subscriptionId,double totalReceivableCost,LocalDate costChangeDate) {
        super(totalReceivableCost,costChangeDate);
        this.subscriptionId=subscriptionId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }


}
