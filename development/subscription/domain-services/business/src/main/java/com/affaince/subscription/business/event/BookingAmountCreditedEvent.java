package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BookingAmountCreditedEvent extends CreditedEvent {
    public BookingAmountCreditedEvent() {
    }

    public BookingAmountCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
