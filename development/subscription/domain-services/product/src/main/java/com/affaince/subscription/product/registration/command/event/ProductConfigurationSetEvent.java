package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductConfigurationSetEvent {
    private String productId;
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private double merchantExpectedProfitPercent;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public ProductConfigurationSetEvent(String productId, Period demandCurvePeriod, short revenueChangeThresholdForPriceChange, double merchantExpectedProfitPercent, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered) {
        this.productId = productId;
        this.demandCurvePeriod = demandCurvePeriod;
        this.revenueChangeThresholdForPriceChange = revenueChangeThresholdForPriceChange;
        this.merchantExpectedProfitPercent = merchantExpectedProfitPercent;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
    }

    public ProductConfigurationSetEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public short getRevenueChangeThresholdForPriceChange() {
        return revenueChangeThresholdForPriceChange;
    }

    public double getMerchantExpectedProfitPercent() {
        return merchantExpectedProfitPercent;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }
}
