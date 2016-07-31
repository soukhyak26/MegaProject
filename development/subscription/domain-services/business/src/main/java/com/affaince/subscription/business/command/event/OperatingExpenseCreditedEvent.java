package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public abstract class OperatingExpenseCreditedEvent extends CreditedEvent {
    public OperatingExpenseCreditedEvent() {
    }

    protected OperatingExpenseCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
