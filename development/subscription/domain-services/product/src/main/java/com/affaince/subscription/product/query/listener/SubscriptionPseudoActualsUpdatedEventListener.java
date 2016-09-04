package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionPseudoActualsUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 14-08-2016.
 */
@Component
public class SubscriptionPseudoActualsUpdatedEventListener {

    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;

    @Autowired
    public SubscriptionPseudoActualsUpdatedEventListener(ProductPseudoActualsViewRepository productPseudoActualsViewRepository) {
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
    }

    @EventHandler
    public void on(SubscriptionPseudoActualsUpdatedEvent event) {
        final ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(
                new ProductVersionId(event.getProductId(), event.getForecastStartDate()),
                event.getForecastEndDate(),
                event.getNewSubscriptionForecast(),
                event.getChurnedSubscriptionForecast(),
                event.getForecastedTotalSubscriptionCount()
        );
        productPseudoActualsViewRepository.save(productPseudoActualsView);
    }
}
