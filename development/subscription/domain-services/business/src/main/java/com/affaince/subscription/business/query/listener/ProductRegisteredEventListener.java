package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProductRegisteredEvent;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
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
                event.getSensitiveTo(),
                event.getPurchasePrice(),
                event.getMRP(),
                ProductStatus.PRODUCT_REGISTERED
        );
        itemRepository.save(productView);
    }
}
