package com.affaince.subscription.product.command.event;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class ProductPricingConfigurationSetEvent {
    private String productId;
    private double targetChangeThresholdForPriceChange;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;

    public ProductPricingConfigurationSetEvent(String productId, double targetChangeThresholdForPriceChange, boolean isCrossPriceElasticityConsidered, boolean isAdvertisingExpensesConsidered) {
        this.productId = productId;
        this.targetChangeThresholdForPriceChange = targetChangeThresholdForPriceChange;
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
    }

    public ProductPricingConfigurationSetEvent() {
    }

    public String getProductId() {
        return productId;
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

}
