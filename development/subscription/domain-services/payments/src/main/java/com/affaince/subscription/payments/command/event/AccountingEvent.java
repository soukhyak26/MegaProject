package com.affaince.subscription.payments.command.event;

/**
 * Created by anayonkar on 23/8/16.
 */
public abstract class AccountingEvent {
    private String id;

    protected AccountingEvent() {
    }

    protected AccountingEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
