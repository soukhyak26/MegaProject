package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class CommonOperatingExpenseAddedEvent {
    private String id;
    private String expenseHeader;
    private double amount;
    private Period period;

    public CommonOperatingExpenseAddedEvent(String id, String expenseHeader, double amount, Period period) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
    }

    public CommonOperatingExpenseAddedEvent() {
    }

    public String getId() {
        return id;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public double getAmount() {
        return amount;
    }

    public Period getPeriod() {
        return period;
    }
}
