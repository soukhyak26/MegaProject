package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class PurchaseCostCreditedEvent extends CreditedEvent {
    public PurchaseCostCreditedEvent() {
    }

    public PurchaseCostCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
