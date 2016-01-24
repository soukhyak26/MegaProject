package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.type.Period;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseTypeAddedEvent {
    private String commonOperatingExpenseId;
    private String expenseHeader;
    private double amount;
    private Period period;
    private YearMonth monthOfYear;

    public CommonExpenseTypeAddedEvent(String id, String expenseHeader, double amount, Period period, YearMonth monthOfYear) {
        this.commonOperatingExpenseId = id;
        this.expenseHeader = expenseHeader;
        this.amount = amount;
        this.period = period;
        this.monthOfYear = monthOfYear;
    }

    public String getExpenseHeader() {
        return this.expenseHeader;
    }

    public double getAmount() {
        return this.amount;
    }

    public Period getPeriod() {
        return this.period;
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }
}
