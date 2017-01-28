package com.affaince.subscription.product.command.event;

/**
 * Created by mandar on 28-01-2017.
 */
public class ProductRegisteredPurchaseCostRevenueAndProfitCalculatedEvent {
    private String productId;
    private double registeredPurchaseCost;
    private double registeredRevenue;
    private double registeredProfit;
    public ProductRegisteredPurchaseCostRevenueAndProfitCalculatedEvent(String productId, double registeredPurchaseCost, double registeredRevenue, double registeredProfit) {
        this.productId=productId;
        this.registeredPurchaseCost=registeredPurchaseCost;
        this.registeredRevenue=registeredRevenue;
        this.registeredProfit=registeredProfit;
    }

    public String getProductId() {
        return productId;
    }

    public double getRegisteredPurchaseCost() {
        return registeredPurchaseCost;
    }

    public double getRegisteredRevenue() {
        return registeredRevenue;
    }

    public double getRegisteredProfit() {
        return registeredProfit;
    }
}
