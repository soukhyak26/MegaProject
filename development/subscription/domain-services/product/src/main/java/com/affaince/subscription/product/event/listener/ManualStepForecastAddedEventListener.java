package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.event.ManualStepForecastAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public void on(ManualStepForecastAddedEvent event) {
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.STEPFORECASTABLE
        )) {
            ProductActivationStatusView view = productActivationStatusViewRepository.findOne(event.getProductId());
            if (!view.getProductStatuses().contains(ProductStatus.PRODUCT_STEPFORECAST_CREATED)) {
                view.addProductStatus(ProductStatus.PRODUCT_STEPFORECAST_CREATED);
                productActivationStatusViewRepository.save(view);
            }
        } else {
            ProductReadinessException.build(event.getProductId(), ProductStatus.PRODUCT_STEPFORECAST_CREATED);
        }
    }
}
