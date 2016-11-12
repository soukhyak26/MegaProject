package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ProductActivatedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 11-11-2016.
 */
@Component
public class ProductActivatedEventListener {
    private ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProductActivatedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }


    @EventHandler
    public void on(ProductActivatedEvent event) {
        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_ACTIVATED);
        productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
