package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;

/**
 * Created by anayonkar on 8/5/16.
 */
public abstract class OperatingExpenseCreditedEvent extends CreditedEvent {
    protected OperatingExpenseCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
