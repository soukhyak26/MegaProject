package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ProductRegisteredEvent;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,
                                          ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.itemRepository = repository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }


    @EventHandler
    public void on(ProductRegisteredEvent event) throws InvalidProductStatusException {
        final ProductView productView = new ProductView(
                event.getProductId(),
                event.getProductName(),
                event.getCategoryId(),
                event.getSubCategoryId(),
                event.getQuantity(),
                event.getQuantityUnit(),
                event.getSubstitutes(),
                event.getComplements()
        );
        itemRepository.save(productView);
        final ProductActivationStatusView productActivationStatusView = new ProductActivationStatusView(event.getProductId(), new ArrayList<>());
        boolean result = productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
        productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
