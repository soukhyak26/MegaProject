package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.CurrentOfferedPriceAddedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
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
        productView.getPriceParameters().setCurrentOfferedPrice(event.getCurrentOfferedPrice());
        itemRepository.save(productView);
*/
    }
}
