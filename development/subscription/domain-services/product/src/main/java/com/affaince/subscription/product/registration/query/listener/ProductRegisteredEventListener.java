package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.ProductRegisteredEvent;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;

    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository) {
        this.itemRepository = repository;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event) {
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getProductName(),
                event.getCategoryId(),
                event.getSubCategoryId(),
                event.getQuantity(),
                event.getQuantityUnit()
        );
        itemRepository.save(productView);
    }
}
