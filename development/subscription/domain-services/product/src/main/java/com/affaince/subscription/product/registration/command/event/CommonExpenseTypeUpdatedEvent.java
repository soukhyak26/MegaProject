package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

/**
 * Created by mandark on 24-01-2016.
 */
public class CommonExpenseTypeUpdatedEvent {
    private String commonOperatingExpenseId;
    private ExpenseType expenseType;
    private String expenseHeader;
    private double expenseAmount;
    private Period period;
    private int forMonth;
    private int forYear;

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public int getForMonth() {
        return this.forMonth;
    }

    public void setForMonth(int forMonth) {
        this.forMonth = forMonth;
    }

    public int getForYear() {
        return this.forYear;
    }

    public void setForYear(int forYear) {
        this.forYear = forYear;
    }

    @Override
    public String toString() {
        return "CommonExpenseTypeUpdatedEvent{" +
                "expenseType=" + expenseType +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", period=" + period +
                ", forMonth=" + forMonth +
                ", forYear=" + forYear +
                '}';
    }

    public String getCommonOperatingExpenseId() {
        return this.commonOperatingExpenseId;
    }

    public void setCommonOperatingExpenseId(String commonOperatingExpenseId) {
        this.commonOperatingExpenseId = commonOperatingExpenseId;
    }
}
