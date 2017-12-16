package com.affaince.subscription.payments.domain.accounting;

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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }


}
