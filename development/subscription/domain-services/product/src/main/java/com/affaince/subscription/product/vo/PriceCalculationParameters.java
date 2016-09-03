package com.affaince.subscription.product.vo;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.query.view.ProductActualsView;

/**
 * Created by mandar on 27-08-2016.
 */
public final class PriceCalculationParameters {
    private final Product product;
    private final ProductActualsView productActualsView;
    private final ProductDemandTrend productDemandTrend;
    private final double changeThresholdPercentageForPriceChange;
    private final PricingStrategyType pricingStrategyType;

    public PriceCalculationParameters(Product product, ProductActualsView productActualsView, ProductDemandTrend productDemandTrend, double changeThresholdPercentageForPriceChange, PricingStrategyType pricingStrategyType) {
        this.product = product;
        this.productActualsView = productActualsView;
        this.productDemandTrend = productDemandTrend;
        this.changeThresholdPercentageForPriceChange = changeThresholdPercentageForPriceChange;
        this.pricingStrategyType = pricingStrategyType;
    }

    public Product getProduct() {
        return product;
    }

    public ProductActualsView getProductActualsView() {
        return productActualsView;
    }

    public ProductDemandTrend getProductDemandTrend() {
        return productDemandTrend;
    }

    public double getChangeThresholdPercentageForPriceChange() {
        return changeThresholdPercentageForPriceChange;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }
}
