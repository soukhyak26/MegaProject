package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ProductActivatedEvent;
import com.affaince.subscription.business.query.repository.ProductViewRepository;
import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 11-11-2016.
 */
@Component
public class ProductActivatedEventListener {
    private ProductViewRepository productViewRepository;

    @Autowired
    public ProductActivatedEventListener(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }


    @EventHandler
    public void on(ProductActivatedEvent event) {
        final ProductView productView = productViewRepository.findByProductId(event.getProductId());
        productView.setProductStatus(ProductStatus.PRODUCT_ACTIVATED);
        productViewRepository.save(productView);
    }
}
