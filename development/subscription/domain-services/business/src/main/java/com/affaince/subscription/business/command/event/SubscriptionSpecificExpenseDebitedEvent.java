package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public class SubscriptionSpecificExpenseDebitedEvent extends OperatingExpenseDebitedEvent {
    public SubscriptionSpecificExpenseDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
