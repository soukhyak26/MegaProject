package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.event.SubscriptionRuleAddedEvent;
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
public class SubscriptionRuleAddedEventListener {

    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public SubscriptionRuleAddedEventListener(ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(SubscriptionRuleAddedEvent event) {
        final List<ProductActivationStatusView> productActivationStatusViews =
                new ArrayList<>();
        productActivationStatusViewRepository.findAll().forEach(
                productActivationStatusView -> productActivationStatusViews.add(productActivationStatusView)
        );
        productActivationStatusViews.forEach(productActivationStatusView -> {
            if (ProductConfigurationValidator.getProductReadinessStatus(productActivationStatusView.getProductStatuses()).contains(
                    ProductReadinessStatus.SUBSCRIPTION_RULES_CONFIGURABLE
            )) {
                productActivationStatusView.addProductStatus(ProductStatus.SUBSCRIPTION_RULES_CONFIGURED);
                productActivationStatusViewRepository.save(productActivationStatusView);
            } else {
                ProductReadinessException.build(productActivationStatusView.getProductId(), ProductStatus.SUBSCRIPTION_RULES_CONFIGURED);
            }
        });
    }
}
