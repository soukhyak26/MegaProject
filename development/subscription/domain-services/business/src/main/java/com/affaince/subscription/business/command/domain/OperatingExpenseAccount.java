package com.affaince.subscription.business.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class OperatingExpenseAccount extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    private double yearlyCommonOperationExpenses;
    private double yearlySubscriptionSpecificOperatingExpenses;

    public OperatingExpenseAccount() {
    }

    public OperatingExpenseAccount(String id, double yearlyCommonOperationExpenses, double yearlySubscriptionSpecificOperatingExpenses) {
        this.id = id;
        this.yearlyCommonOperationExpenses = yearlyCommonOperationExpenses;
        this.yearlySubscriptionSpecificOperatingExpenses = yearlySubscriptionSpecificOperatingExpenses;
    }

    /*public void addCommonExpense(CommonOperatingExpense expense) {
        commonExpenses.add(expense);
    }

    public void addSubscriptionSpeciffcExpense(SubscriptionSpecificOperatingExpense expense) {
        subscriptionSpecificOperatingExpenses.add(expense);
    }

    public double calculateTotalMonthlyCommonExpenses() {
        double totalCommonExpenses = 0;
        for (CommonOperatingExpense expense : commonExpenses) {
            totalCommonExpenses += expense.transalateExpenseAmountToMonthlyExpense();
        }
        return totalCommonExpenses;

    }*/

    public double calculateTotalMonthlySubscriptionSpecificExpenses() {
        return 0;
    }
}
