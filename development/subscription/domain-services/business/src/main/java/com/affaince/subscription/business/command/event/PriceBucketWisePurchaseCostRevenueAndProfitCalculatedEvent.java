package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.ProductPricingCategory;

/**
 * Created by mandar on 3/25/2017.
 */
public class PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent {
    private final String productId;
    private final String priceBucketId;
    private final ProductPricingCategory productPricingCategory;
    private final double fixedExpensePerUnit;
    private final double variableExpensePerUnit;
    private final long deliveredSubscriptionCount;
    private final long totalDeliveredSubscriptionCount;
    private final double purchaseCostOfDeliveredUnits;
    private final double revenue;
    private final double profitAmountPerPriceBucket;

    public PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, double fixedExpensePerUnit, double variableExpensePerUnit, long deliveredSubscriptionCount, long totalDeliveredSubscriptionCount, double purchaseCostOfDeliveredUnits, double revenue, double profitAmountPerPriceBucket) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.fixedExpensePerUnit = fixedExpensePerUnit;
        this.variableExpensePerUnit = variableExpensePerUnit;
        this.deliveredSubscriptionCount = deliveredSubscriptionCount;
        this.totalDeliveredSubscriptionCount = totalDeliveredSubscriptionCount;
        this.purchaseCostOfDeliveredUnits = purchaseCostOfDeliveredUnits;
        this.revenue = revenue;
        this.profitAmountPerPriceBucket = profitAmountPerPriceBucket;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }


    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public double getFixedExpensePerUnit() {
        return fixedExpensePerUnit;
    }

    public double getVariableExpensePerUnit() {
        return variableExpensePerUnit;
    }

    public long getDeliveredSubscriptionCount() {
        return deliveredSubscriptionCount;
    }

    public long getTotalDeliveredSubscriptionCount() {
        return totalDeliveredSubscriptionCount;
    }

    public double getPurchaseCostOfDeliveredUnits() {
        return purchaseCostOfDeliveredUnits;
    }

    public double getRevenue() {
        return revenue;
    }

    public double getProfitAmountPerPriceBucket() {
        return profitAmountPerPriceBucket;
    }

}
