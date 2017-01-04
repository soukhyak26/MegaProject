package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.CommonExpenseCreditedEvent;
import com.affaince.subscription.business.command.event.CommonExpenseDebitedEvent;
import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDateTime;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpensesAccount extends AbstractAnnotatedEntity {
    private double startAmount;
    private double currentAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public CommonExpensesAccount(double startAmount, LocalDateTime endDate) {
        this.startAmount = startAmount;
        this.endDate = endDate;
        this.currentAmount = startAmount;
        this.startDate = LocalDateTime.now();

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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    private void handleCreditedEvent(CreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    private void handleDebitedEvent(DebitedEvent event) {
        debit(event.getAmountToDebit());
    }

    public void fireCreditedEvent(Integer businessAccountId, double amountToCredit) {
        apply(new CommonExpenseCreditedEvent(businessAccountId, amountToCredit));
    }

    public void fireDebitedEvent(Integer businessAccountId, double amountToDebit) {
        apply(new CommonExpenseDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(CommonExpenseCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(CommonExpenseDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
