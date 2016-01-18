package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.vo.ProductStatistics;

import java.util.List;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
public class ProductsStatisticsCalculatedEvent {
    private List<ProductStatistics> productsStatistics;

    public ProductsStatisticsCalculatedEvent(List<ProductStatistics> productsStatistics) {
        this.productsStatistics = productsStatistics;
    }

    public ProductsStatisticsCalculatedEvent() {
    }

    public List<ProductStatistics> getProductsStatistics() {
        return productsStatistics;
    }
}
