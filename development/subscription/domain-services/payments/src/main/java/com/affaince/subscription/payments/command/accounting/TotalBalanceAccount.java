package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.TotalBalanceAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.TotalBalanceAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 23/8/16.
 */
public class TotalBalanceAccount extends Account {

    public TotalBalanceAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new TotalBalanceAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new TotalBalanceAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(TotalBalanceAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(TotalBalanceAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
