package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 8/5/16.
 */
public class BookingAmountDebitedEvent extends DebitedEvent {
    public BookingAmountDebitedEvent() {
    }

    public BookingAmountDebitedEvent(Integer businessAccountId, double amountToDebit) {
        super(businessAccountId, amountToDebit);
    }
}
