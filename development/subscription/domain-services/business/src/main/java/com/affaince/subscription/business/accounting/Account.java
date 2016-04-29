package com.affaince.subscription.business.accounting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
public class Account {
    private double startAmount;
    private double currentAmount;
    List<Transaction> transactionList = new ArrayList<>();

    public Account(double startAmount) {
        this.startAmount = startAmount;
        this.currentAmount = startAmount;
    }

    public void debit(double amount) {
        this.currentAmount -= amount;
        transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public double getStartAmount() {
        return startAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
