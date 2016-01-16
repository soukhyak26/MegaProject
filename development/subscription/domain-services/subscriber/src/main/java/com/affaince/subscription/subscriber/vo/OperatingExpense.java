package com.affaince.subscription.subscriber.vo;

import com.affaince.subscription.common.type.ExpenseType;
import com.affaince.subscription.common.type.Period;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class OperatingExpense {
    private String expenseHeader;
    private double amount;
    private Period period;
    private ExpenseType expenseType;
    private List<RangeRule> deliveryChargesRules;


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

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public void setDeliveryChargesRules(List<RangeRule> deliveryChargesRules) {
        this.deliveryChargesRules = deliveryChargesRules;
    }
}
