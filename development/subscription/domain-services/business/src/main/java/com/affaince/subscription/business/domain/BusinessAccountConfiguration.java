package com.affaince.subscription.business.domain;

import com.affaince.subscription.business.vo.BudgetAdjustmentOptions;

/**
 * Created by mandar on 10/8/2017.
 */
public class BusinessAccountConfiguration {
    private Integer businessAccountId;
    private BudgetAdjustmentOptions budgetAdjustmentOptions;

    public BusinessAccountConfiguration(Integer businessAccountId){this.businessAccountId=businessAccountId;}

    public BudgetAdjustmentOptions getBudgetAdjustmentOptions() {
        return budgetAdjustmentOptions;
    }

    public void setBudgetAdjustmentOptions(BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
    }
}
