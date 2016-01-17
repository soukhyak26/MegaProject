package com.affaince.subscription.product.registration.web.request;


import com.affaince.subscription.product.registration.vo.OperatingExpenseVO;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class CommonOperatingExpensesRequest {
    private List<OperatingExpenseVO> expenses;

    public List<OperatingExpenseVO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<OperatingExpenseVO> expenses) {
        this.expenses = expenses;
    }
}
