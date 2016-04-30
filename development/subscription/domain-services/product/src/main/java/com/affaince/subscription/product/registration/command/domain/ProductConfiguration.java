package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.registration.vo.DemandWiseProfitSharingRule;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;

import java.util.List;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfiguration extends AbstractAnnotatedEntity {
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public void setRevenueChangeThresholdForPriceChange(short revenueChangeThresholdForPriceChange) {
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
    }


    public void setCrossPriceElasticityConsidered(boolean crossPriceElasticityConsidered) {
        isCrossPriceElasticityConsidered = crossPriceElasticityConsidered;
    }

    public void setAdvertisingExpensesConsidered(boolean advertisingExpensesConsidered) {
        isAdvertisingExpensesConsidered = advertisingExpensesConsidered;
    }

    public void setDemandWiseProfitSharingRules(List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules) {
        this.demandWiseProfitSharingRules = demandWiseProfitSharingRules;
    }

    public DemandWiseProfitSharingRule findDemandWiseProfitSharingRuleByDemandDensity(double demandDensity) {
        DemandWiseProfitSharingRule rule = new DemandWiseProfitSharingRule();
        rule.setDemandDensityPercentage(demandDensity);
        if (demandWiseProfitSharingRules.contains(rule)) {
            return demandWiseProfitSharingRules.get(demandWiseProfitSharingRules.indexOf(rule));
        }
        return null;
    }
}
