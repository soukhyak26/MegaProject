package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.RevenueCreditedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class RevenueAccount extends Account {
    public RevenueAccount(double startAmount, LocalDate endDate) {
        super(AccountType.REVENUE, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new RevenueCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {

    }

    @EventSourcingHandler
    public void on(RevenueCreditedEvent event) {
        handleCreditedEvent(event);
    }
}
