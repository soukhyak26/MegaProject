package com.affaince.subscription.product.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfiguration extends AbstractAnnotatedEntity {
    //private Period demandCurvePeriod;
    //whether to aggregate daily actuals for daily/weekly/monthly/quarterly Forecast
    private int actualsAggregationPeriodForTargetForecast = 30;
    private short revenueChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    //private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
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

    /*
    public void setDemandWiseProfitSharingRules(List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules) {
        this.demandWiseProfitSharingRules = demandWiseProfitSharingRules;
s    }
*/

/*
    public DemandWiseProfitSharingRule findDemandWiseProfitSharingRuleByDemandDensity(double demandDensity) {
        DemandWiseProfitSharingRule rule = new DemandWiseProfitSharingRule();
        rule.setDemandDensityPercentage(demandDensity);
        if (demandWiseProfitSharingRules.contains(rule)) {
            return demandWiseProfitSharingRules.get(demandWiseProfitSharingRules.indexOf(rule));
        }
        return null;
    }
*/
}
