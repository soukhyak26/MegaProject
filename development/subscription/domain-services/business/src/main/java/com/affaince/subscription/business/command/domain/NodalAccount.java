package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

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

    public void registerProvisionForNodal(Integer id, LocalDate startDate, LocalDate endDate, double provisionForNodal) {
        apply(new ProvisionForNodalRegisteredEvent(id, startDate, endDate, provisionForNodal));
    }

    @EventSourcingHandler
    public void on(ProvisionForNodalRegisteredEvent event ){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForNodal();

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
