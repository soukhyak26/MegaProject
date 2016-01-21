package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rsavaliya on 21/1/16.
 */
public class OperatingExpenseReceivedEvent {
    private ExpenseType expenseType;
    private String expenseHeader;
    private double expenseAmount;
    private Period period;

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

    @Override
    public String toString() {
        return "OperatingExpenseReceivedEvent{" +
                "expenseType=" + expenseType +
                ", expenseHeader='" + expenseHeader + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", period=" + period +
                '}';
    }
}
