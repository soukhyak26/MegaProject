package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.product.vo.PricingStrategyType;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductPricingConfigurationRequest {
    //whether to aggregate daily actuals for daily/weekly/monthly/quarterly Forecast
    private int actualsAggregationPeriodForTargetForecast;
    //percent difference in acutal and forecasted demand which should trigger price calculation
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private PricingOptions pricingOptions;
    private PricingStrategyType pricingStrategyType;
    //How much maximum historical data should be used for foresting ( 6 months,1 year,2 year etc)
    private Period demandCurvePeriod;
    private double tentativePercentageChangeInProductDemand;


    public double getTargetChangeThresholdForPriceChange() {
        return targetChangeThresholdForPriceChange;
    }

    public void setTargetChangeThresholdForPriceChange(double targetChangeThresholdForPriceChange) {
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
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

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public double getTentativePercentageChangeInProductDemand() {
        return tentativePercentageChangeInProductDemand;
    }

    public void setTentativePercentageChangeInProductDemand(double tentativePercentageChangeInProductDemand) {
        this.tentativePercentageChangeInProductDemand = tentativePercentageChangeInProductDemand;
    }
}
