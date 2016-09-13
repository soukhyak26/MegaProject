package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ProductPricingConfigurationSetEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductPricingConfigurationSetEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProductPricingConfigurationSetEventListener(ProductConfigurationViewRepository productConfigurationViewRepository,
                                                       ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventSourcingHandler
    public void on(ProductPricingConfigurationSetEvent event) throws InvalidProductStatusException, ProductNotFoundException {
        //final ProductConfigurationView productConfigurationView = new ProductConfigurationView();
        final ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
        productConfigurationView.setProductId(event.getProductId());
        productConfigurationView.setTargetChangeThresholdForPriceChange(event.getTargetChangeThresholdForPriceChange());
        productConfigurationView.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productConfigurationView.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
        productConfigurationViewRepository.save(productConfigurationView);
        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        if(productActivationStatusView == null) {
            throw ProductNotFoundException.build(event.getProductId());
        }
        boolean result = productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
        productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
