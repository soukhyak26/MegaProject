package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.command.event.ProductConfigurationSetEvent;
import com.affaince.subscription.product.registration.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;
import com.affaince.subscription.product.registration.web.exception.ProductNotFoundException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class ProductConfigurationSetEventListener {

    private final ProductViewRepository productViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public ProductConfigurationSetEventListener(ProductViewRepository productViewRepository,
                                                ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productViewRepository = productViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(ProductConfigurationSetEvent event) throws InvalidProductStatusException, ProductNotFoundException {
        //final ProductConfigurationView productConfigurationView = new ProductConfigurationView();
        final ProductView productView = productViewRepository.findOne(event.getProductId());
        productView.setProductId(event.getProductId());
        productView.setRevenueChangeThresholdForPriceChange(event.getRevenueChangeThresholdForPriceChange());
        productView.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productView.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
        productView.setDemandWiseProfitSharingRules(event.getDemandWiseProfitSharingRules());
        productViewRepository.save(productView);
        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        if(productActivationStatusView == null) {
            throw ProductNotFoundException.build(event.getProductId());
        }
        boolean result = productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
        productActivationStatusViewRepository.save(productActivationStatusView);
    }
}
