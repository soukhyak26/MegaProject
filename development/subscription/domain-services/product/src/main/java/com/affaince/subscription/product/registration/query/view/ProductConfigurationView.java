package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.Period;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Document(collection = "ProductConfigurationView")
public class ProductConfigurationView {
    @Id
    private String productId;
    private Period demandCurvePeriod;
    private short revenueChangeThresholdForPriceChange;
    private double merchantExpectedProfitPercent;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

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
