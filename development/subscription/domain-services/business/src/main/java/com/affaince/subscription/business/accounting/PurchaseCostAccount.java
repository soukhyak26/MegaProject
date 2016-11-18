package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent;
import com.affaince.subscription.business.command.event.PurchaseCostDebitedEvent;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class PurchaseCostAccount extends AbstractAnnotatedEntity {

    private double startAmount;
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public PurchaseCostAccount(double startAmount, LocalDate endDate) {
        this.startAmount = startAmount;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private void handleCreditedEvent(CreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    private void handleDebitedEvent(DebitedEvent event) {
        debit(event.getAmountToDebit());
    }


    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new PurchaseCostCreditedEvent(businessAccountId, amountToCredit));
    }

    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {
        apply(new PurchaseCostDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(PurchaseCostCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(PurchaseCostDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
