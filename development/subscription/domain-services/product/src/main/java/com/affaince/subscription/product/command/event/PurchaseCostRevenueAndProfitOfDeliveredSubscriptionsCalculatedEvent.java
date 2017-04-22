package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.type.ProductPricingCategory;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 4/21/2017.
 */
public class PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent {
    private String productId;
    private String priceBucketId;
    private ProductPricingCategory productPricingCategory;
    private double fixedExpensePerUnit;
    private double variableExpensePerUnit;
    private long deliveredSubscriptionCount;
    private double purchaseCostOfDeliveredSubscriptions;
    private double revenueOfDeliveredSubscriptions;
    private double profitOfDeliveredSubscriptions;
    private LocalDate dispatchDate;

    public PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent(String productId, String priceBucketId, ProductPricingCategory productPricingCategory, double fixedExpensePerUnit, double variableExpensePerUnit, long deliveredSubscriptionCount, double purchaseCostOfDeliveredSubscriptions, double revenueOfDeliveredSubscriptions, double profitOfDeliveredSubscriptions,LocalDate dispatchDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.productPricingCategory = productPricingCategory;
        this.fixedExpensePerUnit = fixedExpensePerUnit;
        this.variableExpensePerUnit = variableExpensePerUnit;
        this.deliveredSubscriptionCount = deliveredSubscriptionCount;
        this.purchaseCostOfDeliveredSubscriptions = purchaseCostOfDeliveredSubscriptions;
        this.revenueOfDeliveredSubscriptions = revenueOfDeliveredSubscriptions;
        this.profitOfDeliveredSubscriptions = profitOfDeliveredSubscriptions;
        this.dispatchDate=dispatchDate;
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

    public double getPurchaseCostOfDeliveredSubscriptions() {
        return purchaseCostOfDeliveredSubscriptions;
    }

    public double getRevenueOfDeliveredSubscriptions() {
        return revenueOfDeliveredSubscriptions;
    }

    public double getProfitOfDeliveredSubscriptions() {
        return profitOfDeliveredSubscriptions;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }
}
