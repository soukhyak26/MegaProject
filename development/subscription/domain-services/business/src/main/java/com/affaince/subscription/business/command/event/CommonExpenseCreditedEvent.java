package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public class CommonExpenseCreditedEvent extends OperatingExpenseCreditedEvent {
    public CommonExpenseCreditedEvent() {
    }

    public CommonExpenseCreditedEvent(Integer businessAccountId, double amountToCredit) {
        super(businessAccountId, amountToCredit);
    }
}
