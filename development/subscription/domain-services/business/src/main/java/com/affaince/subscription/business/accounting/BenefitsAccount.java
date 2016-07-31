package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.BenefitCreditedEvent;
import com.affaince.subscription.business.command.event.BenefitDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class BenefitsAccount extends Account {

    public BenefitsAccount(double startAmount, LocalDate endDate) {
        super(AccountType.BENEFITS, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new BenefitCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {
        apply(new BenefitDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(BenefitCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(BenefitDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
