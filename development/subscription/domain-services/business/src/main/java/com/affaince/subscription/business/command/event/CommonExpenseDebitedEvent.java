package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpenseDebitedEvent extends OperatingExpenseDebitedEvent {
    public CommonExpenseDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
