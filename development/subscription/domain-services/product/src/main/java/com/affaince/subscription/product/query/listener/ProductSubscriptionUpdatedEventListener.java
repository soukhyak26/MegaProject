package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.ProductSubscriptionUpdatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 19-06-2016.
 */
public class ProductSubscriptionUpdatedEventListener {
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;

    public ProductSubscriptionUpdatedEventListener(ProductActualMetricsViewRepository productActualMetricsViewRepository, PriceBucketViewRepository priceBucketViewRepository) {
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @Autowired

    @EventHandler
    public void on(ProductSubscriptionUpdatedEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productMonthlyVersionId.fromDate");
        ProductActualMetricsView productActualMetricsView = productActualMetricsViewRepository.findByProductMonthlyVersionId_ProductId(event.getProductId(), sort).get(0);
        //final ProductMonthlyStatisticsView productActualMetricsView = statsViewRepository.findOne(productStatistics.getProductId());
        long subscribedProductCount = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
        productActualMetricsView.setTotalNumberOfExistingSubscriptions(subscribedProductCount + event.getSubscriptionCount());
        productActualMetricsViewRepository.save(productActualMetricsView);
        PriceBucketView latestPriceBucket= PriceBucketView.getLatestPriceBucket(priceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId()));
        latestPriceBucket.setTotalProfit(event.getExpectedProfitPerPriceBucket());
        priceBucketViewRepository.save(latestPriceBucket);
    }
}
