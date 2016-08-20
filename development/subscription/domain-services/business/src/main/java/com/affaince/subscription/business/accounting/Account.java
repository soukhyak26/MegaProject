package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 29/4/16.
 */
public abstract class Account extends AbstractAnnotatedEntity {
    private double startAmount;
    private double currentAmount;
    private AccountType accountType;
    private LocalDate startDate;
    private LocalDate endDate;
    //List<Transaction> transactionList = new ArrayList<>();

    /*public Account(AccountType accountType, double startAmount) {
        this.accountType = accountType;
        this.startAmount = startAmount;
        this.currentAmount = startAmount;
    }*/

    protected Account(AccountType accountType, double startAmount, LocalDate endDate) {
        this.startAmount = startAmount;
        this.accountType = accountType;
        this.endDate = endDate;
        this.currentAmount = startAmount;
        this.startDate = SysDate.now();
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

    public AccountType getAccountType() {
        return accountType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    /*public List<Transaction> getTransactionList() {
        return transactionList;
    }*/

    public abstract void fireCreditedEvent(String businessAccountId, double amountToCredit);

    public abstract void fireDebitedEvent(String businessAccountId, double amountToDebit);

    protected void handleCreditedEvent(CreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    protected void handleDebitedEvent(DebitedEvent event) {
        debit(event.getAmountToDebit());
    }
}
