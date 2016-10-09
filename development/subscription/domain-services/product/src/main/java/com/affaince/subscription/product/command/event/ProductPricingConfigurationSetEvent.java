package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.product.vo.PricingStrategyType;


public class ProductPricingConfigurationSetEvent {
    private String productId;
    //whether to aggregate daily actuals for daily/weekly/monthly/quarterly Forecast
    private int actualsAggregationPeriodForTargetForecast = 30;
    //percent difference in acutal and forecasted demand which should trigger price calculation
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private PricingOptions pricingOptions;
    private PricingStrategyType pricingStrategyType;
    //How much maximum historical data should be used for foresting ( 6 months,1 year,2 year etc)
    private Period demandCurvePeriod;

    public ProductPricingConfigurationSetEvent(String productId, int actualsAggregationPeriodForTargetForecast, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType, Period demandCurvePeriod) {
        this.productId = productId;
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
        this.pricingOptions = pricingOptions;
        this.pricingStrategyType = pricingStrategyType;
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public String getProductId() {
        return productId;
    }

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public double getTargetChangeThresholdForPriceChange() {
        return targetChangeThresholdForPriceChange;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }
}
