package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class AccountingEvent {
    private String businessAccountId;

    protected AccountingEvent(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }
}
