package com.affaince.subscription.business.command.event;

import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseCreatedEvent {
    private String commonOperatingExpenseId;
    private String expenseHeader;
    private double amount;
    private YearMonth monthOfYear;

    public CommonExpenseCreatedEvent(String id, String expenseHeader, double amount, YearMonth monthOfYear) {
        this.commonOperatingExpenseId = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.monthOfYear = monthOfYear;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }
}
