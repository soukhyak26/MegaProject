package com.affaince.subscription.business.web.request;

import com.affaince.subscription.business.vo.BudgetAdjustmentOptions;

/**
 * Created by mandar on 10/8/2017.
 */
public class ConfigureBusinessAccountRequest {

    private BudgetAdjustmentOptions budgetAdjustmentOptions;

    public BudgetAdjustmentOptions getBudgetAdjustmentOptions() {
        return budgetAdjustmentOptions;
    }

    public void setBudgetAdjustmentOptions(BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
    }
}
