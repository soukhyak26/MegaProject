package com.affaince.subscription.product.command;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.common.type.PricingStrategyType;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;


public class SetProductPricingConfigurationCommand {
    @TargetAggregateIdentifier
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
    //private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;
    private double tentativePercentageChangeInProductDemand;


    public SetProductPricingConfigurationCommand(String productId, int actualsAggregationPeriodForTargetForecast, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType, Period demandCurvePeriod,double tentativePercentageChangeInProductDemand) {
        this.productId = productId;
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
        this.pricingOptions = pricingOptions;
        this.pricingStrategyType = pricingStrategyType;
        this.demandCurvePeriod = demandCurvePeriod;
        this.tentativePercentageChangeInProductDemand=tentativePercentageChangeInProductDemand;
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

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }


    public double getTentativePercentageChangeInProductDemand() {
        return tentativePercentageChangeInProductDemand;
    }
}
