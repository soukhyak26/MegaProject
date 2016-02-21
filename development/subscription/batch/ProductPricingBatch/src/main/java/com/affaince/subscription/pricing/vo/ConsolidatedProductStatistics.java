package com.affaince.subscription.pricing.vo;

import com.affaince.subscription.pricing.query.view.ProductStatisticsView;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mandark on 21-02-2016.
 */
public class ConsolidatedProductStatistics {
    private String productId;
    private Set<ProductStatisticsView> productStatistics;

    public ConsolidatedProductStatistics(){}
    public ConsolidatedProductStatistics(String productId) {
        this.productId = productId;
        this.productStatistics= new TreeSet<ProductStatisticsView>();
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Set<ProductStatisticsView> getProductStatistics() {
        return this.productStatistics;
    }

    public void setProductStatistics(Set<ProductStatisticsView> productStatistics) {
        this.productStatistics = productStatistics;
    }

    public void addToProductStatistics(ProductStatisticsView productStatisticsView){
            productStatistics.add(productStatisticsView);
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
