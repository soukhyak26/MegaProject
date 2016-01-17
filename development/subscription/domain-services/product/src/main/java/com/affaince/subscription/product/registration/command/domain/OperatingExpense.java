package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.product.registration.command.event.SubscriptionSpecificExpenseAddedEvent;
import com.affaince.subscription.product.registration.vo.RangeRule;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class OperatingExpense extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String id;
    List<CommonOperatingExpense> commonExpenses;
    List<SubscriptionSpecificOperatingExpense> subscriptionSpecificOperatingExpenses;

    public OperatingExpense(){}

    public OperatingExpense(String expenseId){
        commonExpenses=new ArrayList<>();
        subscriptionSpecificOperatingExpenses= new ArrayList<>();
    }
    public void addCommonExpense(CommonOperatingExpense expense){
        commonExpenses.add(expense);
    }

    public void addSubscriptionSpeciffcExpense(SubscriptionSpecificOperatingExpense expense){
            subscriptionSpecificOperatingExpenses.add(expense);
    }

    public double calculateTotalMonthlyCommonExpenses(){
        double totalCommonExpenses=0;
        for(CommonOperatingExpense expense:commonExpenses){
            totalCommonExpenses+=expense.transalateExpenseAmountToMonthlyExpense();
        }
        return totalCommonExpenses;

    }

    public double calculateTotalMonthlySubscriptionSpecificExpenses(){
        return 0;
    }
}
