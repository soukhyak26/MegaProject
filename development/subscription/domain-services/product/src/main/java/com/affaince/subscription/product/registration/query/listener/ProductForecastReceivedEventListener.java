package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.product.registration.command.event.ProductForecastReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class ProductForecastReceivedEventListener {


    @Autowired
    public ProductForecastReceivedEventListener(ProductViewRepository itemRepository) {
    }

    @EventHandler
    public void on(ProductForecastReceivedEvent event) throws Exception {
    }
}
