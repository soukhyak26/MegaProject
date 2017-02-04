package com.affaince.subscription.business.command.domain;

import com.affaince.subscription.business.command.event.BookingAmountCreditedEvent;
import com.affaince.subscription.business.command.event.BookingAmountDebitedEvent;
import com.affaince.subscription.business.command.event.CreditedEvent;
import com.affaince.subscription.business.command.event.DebitedEvent;
import com.affaince.subscription.date.SysDate;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
//For the advance payment makers,total booking amount should be kept here
public class BookingAmountAccount extends AbstractAnnotatedEntity {
    private double currentAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    public BookingAmountAccount(LocalDate startDate,LocalDate endDate) {
        this.startDate=startDate;
        this.endDate = endDate;
    }
    public void debit(double amount) {
        this.currentAmount -= amount;
        //transactionList.add(new Transaction(amount, TransactionType.DEBIT, currentAmount));
    }

    public void credit(double amount) {
        this.currentAmount += amount;
        //transactionList.add(new Transaction(amount, TransactionType.CREDIT, currentAmount));
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


    public void creditToBookingAmount(Integer businessAccountId, double amountToCredit) {
        apply(new BookingAmountCreditedEvent(businessAccountId, amountToCredit));
    }

    public void debitFromBookingAmount(Integer businessAccountId, String contributorId,double amountToDebit) {
        apply(new BookingAmountDebitedEvent(businessAccountId, contributorId,amountToDebit, SysDate.now()));
    }

    @EventSourcingHandler
    public void on(BookingAmountCreditedEvent event) {
        credit(event.getAmountToCredit());
    }

    @EventSourcingHandler
    public void on(BookingAmountDebitedEvent event) {
        debit(event.getAmountToDebit());
    }
}
