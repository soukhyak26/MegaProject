package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.vo.DemandWiseProfitSharingRule;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfigurationRequest {
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private DemandWiseProfitSharingRule[] demandWiseProfitSharingRules;

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public short getRevenueChangeThresholdForPriceChange() {
        return revenueChangeThresholdForPriceChange;
    }

    public void setRevenueChangeThresholdForPriceChange(short revenueChangeThresholdForPriceChange) {
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public void setCrossPriceElasticityConsidered(boolean crossPriceElasticityConsidered) {
        isCrossPriceElasticityConsidered = crossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public void setAdvertisingExpensesConsidered(boolean advertisingExpensesConsidered) {
        isAdvertisingExpensesConsidered = advertisingExpensesConsidered;
    }

    public DemandWiseProfitSharingRule[] getDemandWiseProfitSharingRules() {
        return demandWiseProfitSharingRules;
    }

    public void setDemandWiseProfitSharingRules(DemandWiseProfitSharingRule[] demandWiseProfitSharingRules) {
        this.demandWiseProfitSharingRules = demandWiseProfitSharingRules;
    }
}
