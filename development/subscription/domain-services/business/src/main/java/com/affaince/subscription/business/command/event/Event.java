package com.affaince.subscription.business.command.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class Event {
    private String businessAccountId;

    protected Event(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }
}
