package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.product.event.ProductForecastReceivedEvent;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
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
