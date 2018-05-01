package com.verifier.domains.product.view;

/**
 * Created by mandark on 28-01-2016.
 */
public class ProductForecastMetricsForOpExView {
    private String productId;
    private double mrp;
    private long totalNumberOfExistingSubscriptions;

    public ProductForecastMetricsForOpExView() {
    }

    public ProductForecastMetricsForOpExView(String productId, double mrp, long totalNumberOfExistingSubscriptions) {
        this.productId = productId;
        this.mrp = mrp;
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public long getTotalNumberOfExistingSubscriptions() {
        return totalNumberOfExistingSubscriptions;
    }

    public void setTotalNumberOfExistingSubscriptions(long totalNumberOfExistingSubscriptions) {
        this.totalNumberOfExistingSubscriptions = totalNumberOfExistingSubscriptions;
    }
}
