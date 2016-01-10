package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.SubscribedProductCountUpdatedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-01-2016.
 */
@Component
public class SubscribedProductCountUpdatedEventListener {

    private final ProductViewRepository productViewRepository;

    @Autowired
    public SubscribedProductCountUpdatedEventListener(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }

    @EventHandler
    public void on (SubscribedProductCountUpdatedEvent event) {
        for (String productId: event.getSubscribedProductUpdateCount().keySet()) {
            final ProductView productView = productViewRepository.findOne(productId);
            long subscribedProductCount = productView.getSubscribedProductCount();
            productView.setSubscribedProductCount(subscribedProductCount + event.getSubscribedProductUpdateCount().get(productId));
            productViewRepository.save(productView);
        }
    }
}
