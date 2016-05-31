package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.vo.ProductStatistics;
import com.affaince.subscription.product.registration.command.event.ProductsStatisticsCalculatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductsStatisticsCalculatedEventListener {

    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    public ProductsStatisticsCalculatedEventListener(ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
    }

    //not a correct implementation
    @EventHandler
    public void on(ProductsStatisticsCalculatedEvent event) {
        for (ProductStatistics productStatistics : event.getProductsStatistics()) {
            Sort sort = new Sort(Sort.Direction.DESC, "productMonthlyVersionId.fromDate");
            ProductActualMetricsView productStatsView = productActualMetricsViewRepository.findByProductMonthlyVersionId_ProductId(productStatistics.getProductId(),sort).get(0);

            //final ProductMonthlyStatisticsView productStatsView = statsViewRepository.findOne(productStatistics.getProductId());
            long subscribedProductCount = productStatsView.getTotalNumberOfExistingSubscriptions();
            double subscribedProductRevenue = productStatsView.getRevenue();
            double subscribedProductNetProfit = productStatsView.getOperatingProfit();
            productStatsView.setTotalNumberOfExistingSubscriptions(subscribedProductCount + productStatistics.getProductSubscriptionCount());
            productStatsView.setRevenue(subscribedProductRevenue + productStatistics.getSubscribedProductRevenue());
            productStatsView.setOperatingProfit(subscribedProductNetProfit + productStatistics.getSubscribedProductNetProfit());
            productActualMetricsViewRepository.save(productStatsView);
        }
    }
}
