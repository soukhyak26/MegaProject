package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ProvisionCreatedEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
@Component
public class ProvisionCreatedEventListener {

    private final SubscriptionCommandGateway commandGateway;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProvisionCreatedEventListener(SubscriptionCommandGateway commandGateway, ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.commandGateway = commandGateway;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(ProvisionCreatedEvent event) {
        final List<ProductActivationStatusView> productActivationStatusViews =
                new ArrayList<>();
        productActivationStatusViewRepository.findAll().forEach(
                productActivationStatusView -> productActivationStatusViews.add(productActivationStatusView)
        );
        productActivationStatusViews.forEach(productActivationStatusView -> {
            if (ProductConfigurationValidator.getProductReadinessStatus(productActivationStatusView.getProductStatuses()).contains(
                    ProductReadinessStatus.BUSINESS_PROVISION_CONFIGURABLE
            )) {
                productActivationStatusView.addProductStatus(ProductStatus.BUSINESS_PROVISIONED);
                productActivationStatusViewRepository.save(productActivationStatusView);
            } else {
                ProductReadinessException.build(productActivationStatusView.getProductId(), ProductStatus.BUSINESS_PROVISIONED);
            }
        });
    }
}
