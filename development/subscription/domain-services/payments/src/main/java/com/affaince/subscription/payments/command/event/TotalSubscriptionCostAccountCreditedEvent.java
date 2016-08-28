package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalSubscriptionCostAccountCreditedEvent extends CreditedEvent {
    public TotalSubscriptionCostAccountCreditedEvent() {
    }

    public TotalSubscriptionCostAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
