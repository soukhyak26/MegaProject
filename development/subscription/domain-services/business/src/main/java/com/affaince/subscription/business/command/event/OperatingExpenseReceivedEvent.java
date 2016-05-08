package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

/**
 * Created by anayonkar on 8/5/16.
 */
public class OperatingExpenseReceivedEvent {
    private ExpenseType expenseType;
    private String expenseId;
    private String expenseHeader;
    private double expenseAmount;
    private Period period;
    private int forMonth;
    private int forYear;

    public OperatingExpenseReceivedEvent(ExpenseType expenseType, String expenseId, String expenseHeader, double expenseAmount, Period period, int forMonth, int forYear) {
        this.expenseType = expenseType;
        this.expenseId = expenseId;
        this.expenseHeader = expenseHeader;
        this.expenseAmount = expenseAmount;
        this.period = period;
        this.forMonth = forMonth;
        this.forYear = forYear;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
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
        return forMonth;
    }

    public void setForMonth(int forMonth) {
        this.forMonth = forMonth;
    }

    public int getForYear() {
        return forYear;
    }

    public void setForYear(int forYear) {
        this.forYear = forYear;
    }
}
