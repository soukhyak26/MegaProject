package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.ProductRegisteredEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductRepository itemRepository;

    @Autowired
    public ProductRegisteredEventListener(ProductRepository repository) {
        this.itemRepository = repository;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event) {
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getCategoryId(),
                event.getSubCategoryId()
        );
        itemRepository.save(productView);
    }
}
