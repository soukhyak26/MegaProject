package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10-10-2016.
 */
@Component
public class ManualStepForecastAddedEventListener {
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ManualStepForecastAddedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(ManualForecastAddedEvent event) {
        ProductActivationStatusView view = productActivationStatusViewRepository.findOne(event.getProductId());
        view.addProductStatus(ProductStatus.PRODUCT_STEPFORECAST_CREATED);
        productActivationStatusViewRepository.save(view);
    }
}
