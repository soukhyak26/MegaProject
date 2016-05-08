package com.affaince.subscription.business.accounting;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anayonkar on 29/4/16.
 */
public class Account extends AbstractAnnotatedEntity {
    private double startAmount;
    private double currentAmount;
    private AccountType accountType;
    //List<Transaction> transactionList = new ArrayList<>();

    public Account(AccountType accountType, double startAmount) {
        this.accountType = accountType;
        this.startAmount = startAmount;
        this.currentAmount = startAmount;
    }

    public void debit(double amount) {
        this.currentAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public double getStartAmount() {
        return startAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    /*public List<Transaction> getTransactionList() {
        return transactionList;
    }*/
}
