package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class TaxesCreditedEvent extends CreditedEvent {
    public TaxesCreditedEvent() {
    }

    public TaxesCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
