package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public abstract class OperatingExpenseDebitedEvent extends DebitedEvent {
    public OperatingExpenseDebitedEvent() {
    }

    protected OperatingExpenseDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
