package com.affaince.subscription.product.registration.command.domain;

import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfiguration {
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private double merchantExpectedProfitPercent;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public void setRevenueChangeThresholdForPriceChange(short revenueChangeThresholdForPriceChange) {
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
    }

    public void setMerchantExpectedProfitPercent(double merchantExpectedProfitPercent) {
        this.merchantExpectedProfitPercent = merchantExpectedProfitPercent;
    }

    public void setCrossPriceElasticityConsidered(boolean crossPriceElasticityConsidered) {
        isCrossPriceElasticityConsidered = crossPriceElasticityConsidered;
    }

    public void setAdvertisingExpensesConsidered(boolean advertisingExpensesConsidered) {
        isAdvertisingExpensesConsidered = advertisingExpensesConsidered;
    }
}
