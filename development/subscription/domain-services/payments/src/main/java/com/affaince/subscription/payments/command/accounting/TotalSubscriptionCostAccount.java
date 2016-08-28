package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalSubscriptionCostAccount extends Account {
    public TotalSubscriptionCostAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new TotalSubscriptionCostAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new TotalSubscriptionCostAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(TotalSubscriptionCostAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(TotalSubscriptionCostAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
