package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public class SubscriptionSpecificExpenseCreditedEvent extends OperatingExpenseCreditedEvent {
    public SubscriptionSpecificExpenseCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
