package com.verifier.domains.business.view;

import com.affaince.subscription.common.vo.FiscalYearConfig;
import com.verifier.domains.business.vo.BudgetAdjustmentOptions;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 10/8/2017.
 */
@Document(collection="BusinessAccountConfigurationView")
public class BusinessAccountConfigurationView {
    private String businessAccountId;
    private BudgetAdjustmentOptions budgetAdjustmentOptions;
    private FiscalYearConfig fiscalYearConfig;
    private double taxAsPercentageOfAnnualRevenue;

    public BusinessAccountConfigurationView(String businessAccountId, BudgetAdjustmentOptions budgetAdjustmentOptions, FiscalYearConfig fiscalYearConfig, double taxAsPercentageOfAnnualRevenue) {
        this.businessAccountId = businessAccountId;
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
        this.fiscalYearConfig=fiscalYearConfig;
        this.taxAsPercentageOfAnnualRevenue=taxAsPercentageOfAnnualRevenue;
    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public void setBusinessAccountId(String businessAccountId) {
        this.businessAccountId = businessAccountId;
    }

    public BudgetAdjustmentOptions getBudgetAdjustmentOptions() {
        return budgetAdjustmentOptions;
    }

    public void setBudgetAdjustmentOptions(BudgetAdjustmentOptions budgetAdjustmentOptions) {
        this.budgetAdjustmentOptions = budgetAdjustmentOptions;
    }

    public FiscalYearConfig getFiscalYearConfig() {
        return fiscalYearConfig;
    }

    public void setFiscalYearConfig(FiscalYearConfig fiscalYearConfig) {
        this.fiscalYearConfig = fiscalYearConfig;
    }

    public double getTaxAsPercentageOfAnnualRevenue() {
        return taxAsPercentageOfAnnualRevenue;
    }

    public void setTaxAsPercentageOfAnnualRevenue(double taxAsPercentageOfAnnualRevenue) {
        this.taxAsPercentageOfAnnualRevenue = taxAsPercentageOfAnnualRevenue;
    }
}
