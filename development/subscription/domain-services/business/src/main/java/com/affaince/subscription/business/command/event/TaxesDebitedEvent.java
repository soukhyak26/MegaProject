package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class TaxesDebitedEvent extends DebitedEvent {
    public TaxesDebitedEvent() {
    }

    public TaxesDebitedEvent(String businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
