package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.DeliveryCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.DeliveryCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 23/8/16.
 */
public class DeliveryCostAccount extends Account {

    public DeliveryCostAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new DeliveryCostAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new DeliveryCostAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(DeliveryCostAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(DeliveryCostAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
