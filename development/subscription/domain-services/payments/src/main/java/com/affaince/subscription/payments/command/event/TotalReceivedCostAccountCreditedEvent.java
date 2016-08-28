package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivedCostAccountCreditedEvent extends CreditedEvent {
    public TotalReceivedCostAccountCreditedEvent() {
    }

    public TotalReceivedCostAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
