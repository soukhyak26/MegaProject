package com.affaince.subscription.expensedistribution.query.view;

public class ProductTotalForecastView {
    public String productId;
    public long totalForecast;
    public double purchasePrice;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public long getTotalForecast() {
        return totalForecast;
    }

    public void setTotalForecast(long totalForecast) {
        this.totalForecast = totalForecast;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductTotalForecastView that = (ProductTotalForecastView) o;

        return productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
