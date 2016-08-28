package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalReceivableCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivableCostAccount extends Account {
    public TotalReceivableCostAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new TotalReceivableCostAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new TotalReceivableCostAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(TotalReceivableCostAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(TotalReceivableCostAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
