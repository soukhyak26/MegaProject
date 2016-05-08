package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ExpenseType;

/**
 * Created by anayonkar on 8/5/16.
 */
public class OperatingExpenseDebitedEvent {
    private String businessAccountId;
    private ExpenseType expenseType;
    private double amountToDebit;

    public OperatingExpenseDebitedEvent(String businessAccountId, ExpenseType expenseType, double amountToDebit) {
        this.businessAccountId = businessAccountId;
        this.expenseType = expenseType;
        this.amountToDebit = amountToDebit;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public double getAmountToDebit() {
        return amountToDebit;
    }
}
