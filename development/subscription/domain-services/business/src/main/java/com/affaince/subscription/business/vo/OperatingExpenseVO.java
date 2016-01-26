package com.affaince.subscription.business.vo;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class OperatingExpenseVO {
    private String expenseHeader;
    private double amount;
    private Period period;
    private ExpenseType expenseType;


    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
