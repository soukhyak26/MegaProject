package com.affaince.subscription.business.command.event;

import org.joda.time.YearMonth;

/**
 * Created by anayonkar on 9/5/16.
 */
public abstract class AccountingEvent {
    private String year;

    protected AccountingEvent() {
    }

    protected AccountingEvent(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }
}
