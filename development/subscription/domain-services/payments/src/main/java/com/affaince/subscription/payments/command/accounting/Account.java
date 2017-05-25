package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.event.CreditedEvent;
import com.affaince.subscription.payments.command.event.DebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

public abstract class Account extends AbstractAnnotatedEntity {
    private double amount;
    private LocalDate transactionDate;

    public Account(double amount,LocalDate transactionDate) {
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public void credit(double amount,LocalDate transactionDate) {
        this.amount += amount;
        this.transactionDate =transactionDate ;
    }

    public void debit(double amount,LocalDate transactionDate) {
        this.amount -= amount;
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }


}
