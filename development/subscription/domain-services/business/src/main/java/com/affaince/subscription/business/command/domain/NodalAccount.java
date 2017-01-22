package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.business.command.event.NodalAccountCreditedEvent;
import com.affaince.subscription.business.command.event.NodalAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class NodalAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public NodalAccount(LocalDate startDate,LocalDate endDate,double provisionAmount) {
        this.provisionAmount = provisionAmount;
        this.startDate=startDate;
        this.endDate = endDate;
        this.currentAmount = provisionAmount;
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @EventSourcingHandler
    private void on(NodalAccountCreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    @EventSourcingHandler
    private void on(NodalAccountDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void fireCreditedEvent(Integer businessAccountId, double amountToCredit) {
        apply(new NodalAccountCreditedEvent(businessAccountId,amountToCredit));
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new NodalAccountDebitedEvent(businessAccountId,amountToDebit));
    }
}
