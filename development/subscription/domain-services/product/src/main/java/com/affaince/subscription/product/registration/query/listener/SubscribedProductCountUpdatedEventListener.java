package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.SubscribedProductCountUpdatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductsStatisticsViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductsStatisticsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
@Component
public class SubscribedProductCountUpdatedEventListener {

    private final ProductsStatisticsViewRepository statsViewRepository;

    @Autowired
    public SubscribedProductCountUpdatedEventListener(ProductsStatisticsViewRepository productStatsViewRepository) {
        this.statsViewRepository = productStatsViewRepository;
    }

    @EventHandler
    public void on(SubscribedProductCountUpdatedEvent event) {
        for (String productId : event.getSubscribedProductUpdateCount().keySet()) {
            final ProductsStatisticsView productStatsView = statsViewRepository.findOne(productId);
            long subscribedProductCount = productStatsView.getProductSubscriptionCount();
            productStatsView.setProductSubscriptionCount(subscribedProductCount + event.getSubscribedProductUpdateCount().get(productId));
            statsViewRepository.save(productStatsView);
        }
    }
}
