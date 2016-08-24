package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.PaymentReceivedAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.PaymentReceivedAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by anayonkar on 23/8/16.
 */
public class PaymentReceivedAccount extends Account {

    public PaymentReceivedAccount(double amount) {
        super(amount);
    }

    @Override
    public void fireCreditedEvent(String id, double amountToCredit) {
        apply(new PaymentReceivedAccountCreditedEvent(id, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String id, double amountToDebit) {
        apply(new PaymentReceivedAccountDebitedEvent(id, amountToDebit));
    }

    @EventSourcingHandler
    public void on(PaymentReceivedAccountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(PaymentReceivedAccountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
