package com.affaince.subscription.expensedistribution.vo;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class ProductWiseDeliveryStats {

    private String productId;
    private double totalDeliveryExpense;
    private long totalUnitsSold;
    private double totalOfferedPrice;

    public ProductWiseDeliveryStats(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductWiseDeliveryStats)) return false;

        ProductWiseDeliveryStats that = (ProductWiseDeliveryStats) o;

        return getProductId().equals(that.getProductId());

    }

    @Override
    public int hashCode() {
        return getProductId().hashCode();
    }
}
