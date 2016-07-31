package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;

/**
 * Created by mandark on 24-01-2016.
 */
public class OperatingExpenseUpdatedEvent {
    private String commonOperatingExpenseId;
    private ExpenseType expenseType;
    private String expenseHeader;
    private double expenseAmount;
    private int forMonth;
    private int forYear;

    public OperatingExpenseUpdatedEvent() {
    }

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
        return "OperatingExpenseUpdatedEvent{" +
                "expenseType=" + expenseType +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
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
