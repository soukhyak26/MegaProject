package com.affaince.subscription.expensedistribution.vo;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class ProductWiseDeliveryStats {

    private String productId;
    private double totalDeliveryExpense;
    private long totalUnitsSold;
    private double totalOfferedPrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getTotalDeliveryExpense() {
        return totalDeliveryExpense;
    }

    public void addDeliveryExpense(double deliveryExpense) {
        this.totalDeliveryExpense += deliveryExpense;
    }

    public long getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public void addUnitSold(long unitSold) {
        this.totalUnitsSold += unitSold;
    }

    public double getTotalOfferedPrice() {
        return totalOfferedPrice;
    }

    public void addOfferedPrice(double offeredPrice) {
        this.totalOfferedPrice += offeredPrice;
    }
}
