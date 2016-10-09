package com.affaince.subscription.product.command.domain;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.product.vo.PricingOptions;
import com.affaince.subscription.product.vo.PricingStrategyType;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedEntity;
import org.joda.time.LocalDateTime;

public class ProductConfiguration extends AbstractAnnotatedEntity {
    private String productId;
    //whether to aggregate daily actuals for daily/weekly/monthly/quarterly Forecast
    private int actualsAggregationPeriodForTargetForecast = 30;
    //percent difference in acutal and forecasted demand which should trigger price calculation
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private PricingStrategyType pricingStrategyType;
    private LocalDateTime nextForecastDate;
    //How much maximum historical data should be used for foresting ( 6 months,1 year,2 year etc)
    private Period demandCurvePeriod;
    private PricingOptions pricingOptions;
    //private List<DemandWiseProfitSharingRule> demandWiseProfitSharingRules;


    public ProductConfiguration(String productId, int actualsAggregationPeriodForTargetForecast, Period demandCurvePeriod, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, PricingOptions pricingOptions, PricingStrategyType pricingStrategyType) {
        this.productId = productId;
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
        this.demandCurvePeriod = demandCurvePeriod;
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
        this.pricingOptions = pricingOptions;
        this.pricingStrategyType = pricingStrategyType;
    }

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
    }

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

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public LocalDateTime getNextForecastDate() {
        return nextForecastDate;
    }

    public void setNextForecastDate(LocalDateTime nextForecastDate) {
        this.nextForecastDate = nextForecastDate;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public String getProductId() {
        return productId;
    }


    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public double getDemandCurvePeriodInDays() {
        if (demandCurvePeriod.getUnit() == PeriodUnit.DAY) {
            return demandCurvePeriod.getValue();
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.MONTH) {
            return demandCurvePeriod.getValue() * 30;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.WEEK) {
            return demandCurvePeriod.getValue() * 7;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.YEAR) {
            return demandCurvePeriod.getValue() * 365;
        } else {
            return -1;
        }
    }

    public double getDemandCurvePeriodInWeeks() {
        if (demandCurvePeriod.getUnit() == PeriodUnit.DAY) {
            return demandCurvePeriod.getValue() / 7;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.MONTH) {
            return demandCurvePeriod.getValue() / 4.5;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.WEEK) {
            return demandCurvePeriod.getValue();
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.YEAR) {
            return demandCurvePeriod.getValue() / 54;
        } else {
            return -1;
        }
    }

    public double getDemandCurvePeriodInMonths() {
        if (demandCurvePeriod.getUnit() == PeriodUnit.DAY) {
            return demandCurvePeriod.getValue() / 30;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.MONTH) {
            return demandCurvePeriod.getValue();
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.WEEK) {
            return demandCurvePeriod.getValue() / 4.5;
        } else if (demandCurvePeriod.getUnit() == PeriodUnit.YEAR) {
            return demandCurvePeriod.getValue() / 12;
        } else {
            return -1;
        }
    }


}
