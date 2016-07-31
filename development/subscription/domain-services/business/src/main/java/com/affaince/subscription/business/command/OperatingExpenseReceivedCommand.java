package com.affaince.subscription.business.command;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

/**
 * Created by anayonkar on 8/5/16.
 */
public class OperatingExpenseReceivedCommand {
    private ExpenseType expenseType;
    private String expenseId;
    private String expenseHeader;
    private Double expenseAmount;
    private Period period;
    private Integer forMonth;
    private Integer forYear;

    public OperatingExpenseReceivedCommand(ExpenseType expenseType, String expenseId, String expenseHeader, Double expenseAmount, Period period, Integer forMonth, Integer forYear) {
        this.expenseType = expenseType;
        this.expenseId = expenseId;
        this.expenseHeader = expenseHeader;
        this.expenseAmount = expenseAmount;
        this.period = period;
        this.forMonth = forMonth;
        this.forYear = forYear;
    }

    public OperatingExpenseReceivedCommand(ExpenseType expenseType, Double expenseAmount) {
        this.expenseType = expenseType;
        this.expenseAmount = expenseAmount;
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

    public Double getExpenseAmount() {
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

    public Integer getForMonth() {
        return forMonth;
    }

    public void setForMonth(int forMonth) {
        this.forMonth = forMonth;
    }

    public Integer getForYear() {
        return forYear;
    }

    public void setForYear(int forYear) {
        this.forYear = forYear;
    }

    public String getBusinessAccountId() {
        return this.forYear == null ? null : this.forYear.toString();
    }
}
