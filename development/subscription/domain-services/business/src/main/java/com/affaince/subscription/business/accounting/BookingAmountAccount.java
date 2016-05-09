package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.BookingAmountCreditedEvent;
import com.affaince.subscription.business.command.event.BookingAmountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class BookingAmountAccount extends Account {
    public BookingAmountAccount(double startAmount, LocalDate endDate) {
        super(AccountType.BOOKING_AMOUNT, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new BookingAmountCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {
        apply(new BookingAmountDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(BookingAmountCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(BookingAmountDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
