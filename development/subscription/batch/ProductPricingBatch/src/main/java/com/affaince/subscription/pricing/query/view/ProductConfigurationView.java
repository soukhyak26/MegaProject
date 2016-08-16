package com.affaince.subscription.pricing.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 14-08-2016.
 */
@Document(collection = "ProductConfigurationView")
public class ProductConfigurationView {
    @Id
    private String productId;
    private int actualsAggregationPeriodForTargetForecast = 30;
    private short changeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public ProductConfigurationView(String productId, int actualsAggregationPeriodForTargetForecast, short changeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered) {
        this.productId = productId;
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
        this.changeThresholdForPriceChange = changeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
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

    public short getChangeThresholdForPriceChange() {
        return changeThresholdForPriceChange;
    }

    public void setChangeThresholdForPriceChange(short changeThresholdForPriceChange) {
        this.changeThresholdForPriceChange = changeThresholdForPriceChange;
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
