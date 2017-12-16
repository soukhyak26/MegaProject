package com.affaince.subscription.business.event;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class AccountingEvent {
    private Integer year;

    protected AccountingEvent() {
    }

    protected AccountingEvent(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }
}
