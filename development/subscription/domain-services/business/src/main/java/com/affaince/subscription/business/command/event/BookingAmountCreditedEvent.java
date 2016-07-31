package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BookingAmountCreditedEvent extends CreditedEvent {
    public BookingAmountCreditedEvent() {
    }

    public BookingAmountCreditedEvent(String businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
