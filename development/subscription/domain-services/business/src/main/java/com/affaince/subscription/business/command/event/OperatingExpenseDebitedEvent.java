package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public abstract class OperatingExpenseDebitedEvent extends DebitedEvent {
    public OperatingExpenseDebitedEvent() {
    }

    protected OperatingExpenseDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
