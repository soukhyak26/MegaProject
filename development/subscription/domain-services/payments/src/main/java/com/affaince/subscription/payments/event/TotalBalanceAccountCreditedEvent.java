package com.affaince.subscription.payments.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class TotalBalanceAccountCreditedEvent extends CreditedEvent {
    public TotalBalanceAccountCreditedEvent() {
    }

    public TotalBalanceAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
