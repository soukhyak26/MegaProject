package com.affaince.subscription.business.accounting;

import com.affaince.subscription.business.command.event.CommonExpenseCreditedEvent;
import com.affaince.subscription.business.command.event.CommonExpenseDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpensesAccount extends Account {
    public CommonExpensesAccount(double startAmount, LocalDate endDate) {
        super(AccountType.COMMON_EXPENSES, startAmount, endDate);
    }

    @Override
    public void fireCreditedEvent(String businessAccountId, double amountToCredit) {
        apply(new CommonExpenseCreditedEvent(businessAccountId, amountToCredit));
    }

    @Override
    public void fireDebitedEvent(String businessAccountId, double amountToDebit) {
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
