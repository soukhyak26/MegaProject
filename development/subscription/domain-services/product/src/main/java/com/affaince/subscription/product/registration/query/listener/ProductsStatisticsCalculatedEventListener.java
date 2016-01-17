package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.vo.ProductStatistics;
import com.affaince.subscription.product.registration.command.event.ProductsStatisticsCalculatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductsStatisticsViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductsStatisticsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
@Component
public class ProductsStatisticsCalculatedEventListener {

    private final ProductsStatisticsViewRepository statsViewRepository;

    @Autowired
    public ProductsStatisticsCalculatedEventListener(ProductsStatisticsViewRepository productStatsViewRepository) {
        this.statsViewRepository = productStatsViewRepository;
    }

    @EventHandler
    public void on(ProductsStatisticsCalculatedEvent event) {
        for (ProductStatistics productStatistics : event.getProductsStatistics()) {
            final ProductsStatisticsView productStatsView = statsViewRepository.findOne(productStatistics.getProductId());
            long subscribedProductCount = productStatsView.getProductSubscriptionCount();
            double subscribedProductRevenue = productStatsView.getSubscribedProductRevenue();
            double subscribedProductNetProfit = productStatsView.getSubscribedProductNetProfit();
            productStatsView.setProductSubscriptionCount(subscribedProductCount + productStatistics.getProductSubscriptionCount());
            productStatsView.setSubscribedProductRevenue(subscribedProductRevenue + productStatistics.getSubscribedProductRevenue());
            productStatsView.setSubscribedProductNetProfit(subscribedProductNetProfit + productStatistics.getSubscribedProductNetProfit());
            statsViewRepository.save(productStatsView);
        }
    }
}
