package com.affaince.subscription.product.registration.web.request;

import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfigurationRequest {
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private double merchantExpectedProfitPercent;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

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

    public double getMerchantExpectedProfitPercent() {
        return merchantExpectedProfitPercent;
    }

    public void setMerchantExpectedProfitPercent(double merchantExpectedProfitPercent) {
        this.merchantExpectedProfitPercent = merchantExpectedProfitPercent;
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
}
