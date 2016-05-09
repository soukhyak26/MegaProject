package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;

/**
 * Created by anayonkar on 8/5/16.
 */
public abstract class OperatingExpenseDebitedEvent extends DebitedEvent {
    protected OperatingExpenseDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
