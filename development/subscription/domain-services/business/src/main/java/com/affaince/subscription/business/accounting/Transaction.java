package com.affaince.subscription.business.accounting;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public class Transaction implements Comparable<Transaction> {
    private double amount;
    private TransactionType transactionType;
    private double resultant;
    private LocalDate transactionDate;

    public Transaction(double amount, TransactionType transactionType, double resultant) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.resultant = resultant;
        this.transactionDate = LocalDate.now();
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getResultant() {
        return resultant;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    @Override
    public int compareTo(Transaction o) {
        return transactionDate.compareTo(o.getTransactionDate());
    }
}
