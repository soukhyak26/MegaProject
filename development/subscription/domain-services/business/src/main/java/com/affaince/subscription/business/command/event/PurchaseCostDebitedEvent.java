package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class PurchaseCostDebitedEvent extends DebitedEvent {
    public PurchaseCostDebitedEvent() {
    }

    public PurchaseCostDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
