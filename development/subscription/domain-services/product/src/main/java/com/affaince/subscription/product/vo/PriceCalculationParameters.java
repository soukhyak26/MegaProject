package com.affaince.subscription.product.vo;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;

import java.util.List;

/**
 * Created by mandar on 27-08-2016.
 */
public final class PriceCalculationParameters {
    private final List<PriceBucketView> activePriceBuckets;
    private final ProductActualsView productActualsView;
    private final ProductDemandTrend productDemandTrend;
    private final double changeThresholdPercentageForPriceChange;
    private final PricingStrategyType pricingStrategyType;

    public PriceCalculationParameters(List<PriceBucketView> activePriceBuckets, ProductActualsView productActualsView, ProductDemandTrend productDemandTrend, double changeThresholdPercentageForPriceChange, PricingStrategyType pricingStrategyType) {
        this.activePriceBuckets = activePriceBuckets;
        this.productActualsView = productActualsView;
        this.productDemandTrend = productDemandTrend;
        this.changeThresholdPercentageForPriceChange = changeThresholdPercentageForPriceChange;
        this.pricingStrategyType = pricingStrategyType;
    }

    public List<PriceBucketView> getActivePriceBuckets() {
        return activePriceBuckets;
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
