package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.command.event.ProductRegisteredEvent;
import com.affaince.subscription.product.registration.query.repository.ProductStatusViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductStatusView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class ProductRegisteredEventListener {
    private final ProductViewRepository itemRepository;
    private final ProductStatusViewRepository productStatusViewRepository;

    @Autowired
    public ProductRegisteredEventListener(ProductViewRepository repository,
                                          ProductStatusViewRepository productStatusViewRepository) {
        this.itemRepository = repository;
        this.productStatusViewRepository = productStatusViewRepository;
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
        final ProductStatusView productStatusView = new ProductStatusView(event.getProductId());
        /*ProductStatus latestStatus =*/
        boolean result = productStatusView.addProductStatus(ProductStatus.PRODUCT_REGISTERED);
        /*if(latestStatus.getStatusCode() >= ProductStatus.PRODUCT_COMPLETED.getStatusCode()) {
            //TODO: make use of lastestStatus for firing further event(s) if required
        }*/
        if(result) {

        } else {

        }
        productStatusViewRepository.save(productStatusView);
    }
}
