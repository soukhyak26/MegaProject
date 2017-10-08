package com.affaince.subscription.business.query.view;

import com.affaince.subscription.business.vo.BudgetAdjustmentOptions;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10/8/2017.
 */
@Document(collection="BusinessAccountConfigurationView")
public class BusinessAccountConfigurationView {
    private Integer businessAccountId;
    private BudgetAdjustmentOptions budgetAdjustmentOptions;

    public BusinessAccountConfigurationView(Integer businessAccountId, BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.businessAccountId = businessAccountId;
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
    }

    public Integer getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(Integer businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public BudgetAdjustmentOptions getBudgetAdjustmentOptions() {
        return budgetAdjustmentOptions;
    }

    public void setBudgetAdjustmentOptions(BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
    }
}
