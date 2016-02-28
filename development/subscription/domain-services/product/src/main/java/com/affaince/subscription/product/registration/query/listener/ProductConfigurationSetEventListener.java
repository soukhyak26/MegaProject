package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.command.event.ProductConfigurationSetEvent;
import com.affaince.subscription.product.registration.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductStatusViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductConfigurationView;
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
public class ProductConfigurationSetEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final ProductStatusViewRepository productStatusViewRepository;

    @Autowired
    public ProductConfigurationSetEventListener(ProductConfigurationViewRepository productConfigurationViewRepository,
                                                ProductStatusViewRepository productStatusViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.productStatusViewRepository = productStatusViewRepository;
    }

    @EventHandler
    public void on(ProductConfigurationSetEvent event) throws InvalidProductStatusException, ProductNotFoundException {
        final ProductConfigurationView productConfigurationView = new ProductConfigurationView();
        productConfigurationView.setProductId(event.getProductId());
        productConfigurationView.setDemandCurvePeriod(event.getDemandCurvePeriod());
        productConfigurationView.setRevenueChangeThresholdForPriceChange(event.getRevenueChangeThresholdForPriceChange());
        productConfigurationView.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productConfigurationView.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
        productConfigurationView.setDemandWiseProfitSharingRules(event.getDemandWiseProfitSharingRules());
        productConfigurationViewRepository.save(productConfigurationView);
        final ProductStatusView productStatusView = productStatusViewRepository.findByProductId(event.getProductId());
        if(productStatusView == null) {
            throw ProductNotFoundException.build(event.getProductId());
        }
        /*ProductStatus latestStatus =*/
        boolean result = productStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
        /*if(latestStatus.getStatusCode() >= ProductStatus.PRODUCT_COMPLETED.getStatusCode()) {
            //TODO: make use of lastestStatus for firing further event(s) if required
        }*/
        if(result) {

        } else {

        }
        productStatusViewRepository.save(productStatusView);
    }
}
