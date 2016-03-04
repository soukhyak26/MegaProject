package com.affaince.subscription.pricing.vo;

/**
 * Created by mandark on 04-03-2016.
 */
public class QuantityBasedProductFinancialResult {
    private final long quantity;
    private final double priceAtQuantity;
    private final double costAtQuantity;
    private final double revenueAtQuantity;
    private final double profitAtQuantity;

    public QuantityBasedProductFinancialResult(long quantity, double priceAtQuantity, double costAtQuantity, double revenueAtQuantity, double profitAtQuantity) {
        this.quantity = quantity;
        this.priceAtQuantity = priceAtQuantity;
        this.costAtQuantity = costAtQuantity;
        this.revenueAtQuantity = revenueAtQuantity;
        this.profitAtQuantity = profitAtQuantity;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public double getPriceAtQuantity() {
        return this.priceAtQuantity;
    }

    public double getCostAtQuantity() {
        return this.costAtQuantity;
    }

    public double getRevenueAtQuantity() {
        return this.revenueAtQuantity;
    }

    public double getProfitAtQuantity() {
        return this.profitAtQuantity;
    }
}
