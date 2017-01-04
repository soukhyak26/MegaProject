package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 22/5/16.
 */
public class NodalAccountCreditedEvent extends CreditedEvent {
    public NodalAccountCreditedEvent() {
    }

    public NodalAccountCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
