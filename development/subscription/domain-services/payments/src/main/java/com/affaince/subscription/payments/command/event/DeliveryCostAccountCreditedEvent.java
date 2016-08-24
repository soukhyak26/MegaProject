package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 24/8/16.
 */
public class DeliveryCostAccountCreditedEvent extends CreditedEvent {
    public DeliveryCostAccountCreditedEvent() {
    }

    public DeliveryCostAccountCreditedEvent(String id, double amountToCredit) {
        super(id, amountToCredit);
    }
}
