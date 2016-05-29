package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.vo.ProductStatistics;
import com.affaince.subscription.product.registration.command.event.ProductsStatisticsCalculatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductsMonthlyStatisticsViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductMonthlyStatisticsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductsStatisticsCalculatedEventListener {

    private final ProductsMonthlyStatisticsViewRepository statsViewRepository;

    @Autowired
    public ProductsStatisticsCalculatedEventListener(ProductsMonthlyStatisticsViewRepository productStatsViewRepository) {
        this.statsViewRepository = productStatsViewRepository;
    }

    //not a correct implementation
    @EventHandler
    public void on(ProductsStatisticsCalculatedEvent event) {
        for (ProductStatistics productStatistics : event.getProductsStatistics()) {
            Sort sort = new Sort(Sort.Direction.DESC, "productMonthlyVersionId.fromDate");
            ProductMonthlyStatisticsView productStatsView = statsViewRepository.findByProductMonthlyVersionId_ProductId(productStatistics.getProductId(),sort).get(0);

            //final ProductMonthlyStatisticsView productStatsView = statsViewRepository.findOne(productStatistics.getProductId());
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
