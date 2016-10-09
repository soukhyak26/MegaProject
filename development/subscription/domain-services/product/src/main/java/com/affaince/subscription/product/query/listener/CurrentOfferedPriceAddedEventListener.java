package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.CurrentOfferedPriceAddedEvent;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
@Component
public class CurrentOfferedPriceAddedEventListener {

    private final ProductViewRepository itemRepository;

    @Autowired
    public CurrentOfferedPriceAddedEventListener(ProductViewRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(CurrentOfferedPriceAddedEvent event) {
        ProductView productView = itemRepository.findOne(event.getProductId());
/*
        productView.getPriceParameters().setCurrentOfferedPriceOrPercent(event.getCurrentOfferedPriceOrPercent());
        itemRepository.save(productView);
*/
    }
}
