package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class SubscriptionSpecificExpensesAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public SubscriptionSpecificExpensesAccount(LocalDate startDate,LocalDate endDate) {
        this.startDate=startDate;
        this.endDate = endDate;
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
    }

    public double getProvisionAmount() {
        return provisionAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new SubscriptionSpecificExpenseDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(SubscriptionSpecificExpenseDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    @EventSourcingHandler
    public  void on(ProvisionForSubscriptionSpecificExpensesRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForSubscriptionSpecificExpenses();
    }
}
