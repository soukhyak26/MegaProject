package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalReceivedCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalReceivedCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivedCostAccount extends Account {
    public TotalReceivedCostAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new TotalReceivedCostAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new TotalReceivedCostAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(TotalReceivedCostAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(TotalReceivedCostAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
