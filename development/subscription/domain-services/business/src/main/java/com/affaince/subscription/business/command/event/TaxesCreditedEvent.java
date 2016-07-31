package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class TaxesCreditedEvent extends CreditedEvent {
    public TaxesCreditedEvent() {
    }

    public TaxesCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
