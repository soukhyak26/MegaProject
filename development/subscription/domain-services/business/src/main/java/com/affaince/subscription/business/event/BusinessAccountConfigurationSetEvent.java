package com.affaince.subscription.business.event;

import com.affaince.subscription.business.vo.BudgetAdjustmentOptions;

/**
 * Created by mandar on 10/8/2017.
 */
public class BusinessAccountConfigurationSetEvent {
    private Integer id;
    private BudgetAdjustmentOptions budgetAdjustmentOptions;
    public BusinessAccountConfigurationSetEvent(Integer id, BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.id=id;
        this.budgetAdjustmentOptions=budgetAdjustmentOptions;
    }

    public Integer getId() {
        return id;
    }

    public BudgetAdjustmentOptions getBudgetAdjustmentOptions() {
        return budgetAdjustmentOptions;
    }
}
