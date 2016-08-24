package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.event.CreditedEvent;
import com.affaince.subscription.payments.command.event.DebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 21/8/16.
 */
public abstract class Account extends AbstractAnnotatedEntity {
    private double amount;
    private LocalDate transactionDate;

    public Account(double amount) {
        this.amount = amount;
        transactionDate = SysDate.now();
    }

    public void credit(double amount) {
        this.amount += amount;
        transactionDate = SysDate.now();
    }

    public void debit(double amount) {
        this.amount -= amount;
        transactionDate = SysDate.now();
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public abstract void fireCreditedEvent(String id, double amountToCredit);

    public abstract void fireDebitedEvent(String id, double amountToDebit);

    protected void handleCreditedEvent(CreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    protected void handleDebitedEvent(DebitedEvent event) {
        debit(event.getAmountToDebit());
    }
}
