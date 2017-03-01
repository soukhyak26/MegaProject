package com.affaince.subscription.metrics.vo;

/**
 * Created by mandark on 04-03-2016.
 */
public enum PricingStrategyType {
    DEFAULT_PRICING_STRATEGY(0), DMENAD_BASED_PRICING_STRATEGY(1), DEMAND_AND_COST_BASED_PRICING_STRATEGY(2);
    private int strategyType;

    PricingStrategyType(int strategyType) {
        this.strategyType = strategyType;
    }
}
