package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.SubscriptionSpecificExpenseCreditedEvent;
import com.affaince.subscription.business.command.event.SubscriptionSpecificExpenseDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class SubscriptionSpecificExpensesAccount extends Account {
    public SubscriptionSpecificExpensesAccount(double startAmount, LocalDate endDate) {
        super(AccountType.SUBSCRIPTION_SPECIFIC_EXPENSES, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new SubscriptionSpecificExpenseCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {
        apply(new SubscriptionSpecificExpenseDebitedEvent(businessAccountId, amountToDebit));
    }

    @EventSourcingHandler
    public void on(SubscriptionSpecificExpenseCreditedEvent event) {
        handleCreditedEvent(event);
    }

    @EventSourcingHandler
    public void on(SubscriptionSpecificExpenseDebitedEvent event) {
        handleDebitedEvent(event);
    }
}
