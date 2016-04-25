package com.affaince.subscription.expensedistribution.vo;

/**
 * Created by rsavaliya on 20/3/16.
 */
public enum OperatingExpenseDistributionStrategyType {
    DEFAULT_STRATEGY(0), EXTRAPOLATION_BASED_STRATEGY (1), FORECAST_BASED_STRATEGY (2);

    private int strategyType;

    OperatingExpenseDistributionStrategyType(int strategyType) {
        this.strategyType = strategyType;
    }
}