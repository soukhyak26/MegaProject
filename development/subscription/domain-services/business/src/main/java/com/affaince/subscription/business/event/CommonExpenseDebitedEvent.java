package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpenseDebitedEvent extends OperatingExpenseDebitedEvent {
    public CommonExpenseDebitedEvent() {
    }

    public CommonExpenseDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
