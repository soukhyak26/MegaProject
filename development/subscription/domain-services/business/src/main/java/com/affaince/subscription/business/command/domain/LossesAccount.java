package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.business.command.event.LossesDebitedEvent;
import com.affaince.subscription.business.command.event.ProvisionForLossesRegisteredEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class LossesAccount extends AbstractAnnotatedEntity {
    private double provisionAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public LossesAccount(LocalDate startDate,LocalDate endDate) {
        this.provisionAmount = provisionAmount;
        this.endDate = endDate;
    }
    public void debit(double amount) {
        this.provisionAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
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

    @EventSourcingHandler
    private void on(LossesDebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new LossesDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(ProvisionForLossesRegisteredEvent event){
        this.startDate=event.getStartDate();
        this.endDate=event.getEndDate();
        this.provisionAmount=event.getProvisionForLosses();
    }

}