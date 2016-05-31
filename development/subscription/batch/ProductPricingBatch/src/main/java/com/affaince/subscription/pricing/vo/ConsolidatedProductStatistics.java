package com.affaince.subscription.pricing.vo;

import com.affaince.subscription.pricing.query.view.ProductMonthlyStatisticsView;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mandark on 21-02-2016.
 */
public class ConsolidatedProductStatistics {
    private String productId;
    private Set<ProductMonthlyStatisticsView> productStatistics;

    public ConsolidatedProductStatistics(){}
    public ConsolidatedProductStatistics(String productId) {
        this.productId = productId;
        this.productStatistics= new TreeSet<ProductMonthlyStatisticsView>();
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Set<ProductMonthlyStatisticsView> getProductStatistics() {
        return this.productStatistics;
    }

    public void setProductStatistics(Set<ProductMonthlyStatisticsView> productStatistics) {
        this.productStatistics = productStatistics;
    }

    public void addToProductStatistics(ProductMonthlyStatisticsView productMonthlyStatisticsView){
            productStatistics.add(productMonthlyStatisticsView);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        ConsolidatedProductStatistics that = (ConsolidatedProductStatistics) o;

        return this.getProductId().equals(that.getProductId());

    }

    @Override
    public int hashCode() {
        return this.getProductId().hashCode();
    }
}
