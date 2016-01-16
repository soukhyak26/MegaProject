package com.affaince.subscription.subscriber.web.request;


import com.affaince.subscription.subscriber.vo.OperatingExpense;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpensesRequest {
    private List<OperatingExpense> expenses;

    public List<OperatingExpense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<OperatingExpense> expenses) {
        this.expenses = expenses;
    }
}
