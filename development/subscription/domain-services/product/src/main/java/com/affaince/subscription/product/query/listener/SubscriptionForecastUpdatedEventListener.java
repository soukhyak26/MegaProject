package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 06-07-2016.
 */
public class SubscriptionForecastUpdatedEventListener {
    private final ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    public SubscriptionForecastUpdatedEventListener(ProductForecastViewRepository productForecastViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastUpdatedEvent event) {

        final ProductForecastView productForecastView = new ProductForecastView(
                new ProductVersionId(event.getProductId(), event.getForecastStartDate()),
                event.getForecastEndDate(),
                event.getNewSubscriptionForecast(),
                event.getChurnedSubscriptionForecast(),
                event.getForecastedTotalSubscriptionCount());

        productForecastViewRepository.save(productForecastView);

    }
}
