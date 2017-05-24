package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PricingOptions;
import com.affaince.subscription.common.type.PricingStrategyType;
import com.affaince.subscription.product.vo.CostHeaderType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.EnumSet;
import java.util.List;

/**
 * Created by mandar on 14-08-2016.
 */
@Document(collection = "ProductConfigurationView")
public class ProductConfigurationView {
    @Id
    private String productId;
    private int actualsAggregationPeriodForTargetForecast = 30;
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private PricingStrategyType pricingStrategyType;
    private PricingOptions pricingOptions;
    private LocalDate nextForecastDate;
    //How much maximum historical data should be used for foresting ( 6 months,1 year,2 year etc)
    private Period demandCurvePeriod;
    private double tentativePercentageChangeInProductDemand;
    private List<CostHeaderType> costHeaderTypes;
    public ProductConfigurationView(String productId, int actualsAggregationPeriodForTargetForecast, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered, PricingStrategyType pricingStrategyType, PricingOptions pricingOptions,Period demandCurvePeriod,double tentativePercentageChangeInProductDemand,List<CostHeaderType> costHeaderTypes) {
        this.productId = productId;
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
        this.pricingStrategyType = pricingStrategyType;
        this.pricingOptions = pricingOptions;
        this.demandCurvePeriod=demandCurvePeriod;
        this.tentativePercentageChangeInProductDemand=tentativePercentageChangeInProductDemand;
        this.costHeaderTypes=costHeaderTypes;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public void setTargetChangeThresholdForPriceChange(double changeThresholdForPriceChange) {
        this.targetChangeThresholdForPriceChange = changeThresholdForPriceChange;
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

    public LocalDate getNextForecastDate() {
        return nextForecastDate;
    }

    public void setNextForecastDate(LocalDate nextForecastDate) {
        this.nextForecastDate = nextForecastDate;
    }

    public PricingOptions getPricingOptions() {
        return pricingOptions;
    }

    public void setPricingOptions(PricingOptions pricingOptions) {
        this.pricingOptions = pricingOptions;
    }

    public Period getDemandCurvePeriod() {
        return demandCurvePeriod;
    }

    public void setDemandCurvePeriod(Period demandCurvePeriod) {
        this.demandCurvePeriod = demandCurvePeriod;
    }

    public double getTentativePercentageChangeInProductDemand() {
        return tentativePercentageChangeInProductDemand;
    }

    public void setTentativePercentageChangeInProductDemand(double tentativePercentageChangeInProductDemand) {
        this.tentativePercentageChangeInProductDemand = tentativePercentageChangeInProductDemand;
    }

    public List<CostHeaderType> getCostHeaderTypes() {
        return costHeaderTypes;
    }

    public void setCostHeaderTypes(List<CostHeaderType> costHeaderTypes) {
        this.costHeaderTypes = costHeaderTypes;
    }
}
