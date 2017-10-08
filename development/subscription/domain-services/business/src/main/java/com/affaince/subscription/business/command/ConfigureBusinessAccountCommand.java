package com.affaince.subscription.business.command;

import com.affaince.subscription.business.vo.BudgetAdjustmentOptions;

/**
 * Created by mandar on 10/8/2017.
 */
public class ConfigureBusinessAccountCommand {
    private Integer id;
    private BudgetAdjustmentOptions budgetAdjustmentOptions;
    public ConfigureBusinessAccountCommand(Integer id, BudgetAdjustmentOptions budgetAdjustmentOptions) {
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
