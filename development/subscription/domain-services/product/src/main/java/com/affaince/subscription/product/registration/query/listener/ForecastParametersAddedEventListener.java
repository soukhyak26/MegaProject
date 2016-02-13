package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.command.event.ForecastParametersAddedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductStatusViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductStatusView;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class ForecastParametersAddedEventListener {
    private final ProductStatusViewRepository productStatusViewRepository;

    @Autowired
    public ForecastParametersAddedEventListener(ProductStatusViewRepository productStatusViewRepository) {
        this.productStatusViewRepository = productStatusViewRepository;
    }

    @EventHandler
    public void on(ForecastParametersAddedEvent event) throws ProductNotFoundException, InvalidProductStatusException {
        final ProductStatusView productStatusView = productStatusViewRepository.findOne(event.getProductId());
        if(productStatusView == null) {
            throw ProductNotFoundException.build(event.getProductId());
        }
        productStatusView.addProductStatus(ProductStatus.PRODUCT_FORCASTED);
        productStatusViewRepository.save(productStatusView);
    }
}
