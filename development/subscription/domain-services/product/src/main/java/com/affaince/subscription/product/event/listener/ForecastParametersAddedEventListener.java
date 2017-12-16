package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.event.ForecastParametersAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class ForecastParametersAddedEventListener {
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ForecastParametersAddedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(ForecastParametersAddedEvent event) throws ProductNotFoundException, InvalidProductStatusException {
        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        if(productActivationStatusView == null) {
            throw ProductNotFoundException.build(event.getProductId());
        }
        productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_FORECASTED);
        productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
