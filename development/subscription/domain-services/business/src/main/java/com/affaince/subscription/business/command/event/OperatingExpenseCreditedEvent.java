package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;

/**
 * Created by anayonkar on 8/5/16.
 */
public class OperatingExpenseCreditedEvent {
    private String businessAccountId;
    private ExpenseType expenseType;
    private double amountToCredit;

    public OperatingExpenseCreditedEvent(String businessAccountId, ExpenseType expenseType, double amountToCredit) {
        this.businessAccountId = businessAccountId;
        this.expenseType = expenseType;
        this.amountToCredit = amountToCredit;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public double getAmountToCredit() {
        return amountToCredit;
    }
}
