package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent;
import com.affaince.subscription.business.command.event.PurchaseCostDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class PurchaseCostAccount extends Account {

    public PurchaseCostAccount(double startAmount, LocalDate endDate) {
        super(startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new PurchaseCostCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
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
