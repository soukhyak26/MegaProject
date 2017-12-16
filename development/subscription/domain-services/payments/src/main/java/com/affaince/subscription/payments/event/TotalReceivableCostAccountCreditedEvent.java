package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 28/8/16.
 */
public class TotalReceivableCostAccountCreditedEvent extends CreditedEvent {
    public TotalReceivableCostAccountCreditedEvent() {
    }

    public TotalReceivableCostAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
