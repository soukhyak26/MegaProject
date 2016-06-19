package com.affaince.subscription.product.registration.vo;

/**
 * Created by mandark on 04-03-2016.
 */
public enum PricingStrategyType {
    DEFAULT_PRICING_STRATEGY(0), DEMAND_AND_COST_BASED_PRICING_STRATEGY(1);
    private int strategyType;

    PricingStrategyType(int strategyType) {
        this.strategyType = strategyType;
    }
}
