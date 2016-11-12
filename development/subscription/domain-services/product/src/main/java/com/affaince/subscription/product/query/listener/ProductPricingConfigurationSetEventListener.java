package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.event.ProductPricingConfigurationSetEvent;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;
import com.affaince.subscription.product.web.exception.ProductNotFoundException;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


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

    @EventHandler
    public void on(ProductPricingConfigurationSetEvent event) throws InvalidProductStatusException, ProductNotFoundException {
        final List<ProductStatus> productStatuses = productActivationStatusViewRepository.
                findByProductId(event.getProductId()).getProductStatuses();
        if (ProductConfigurationValidator.getProductReadinessStatus(productStatuses).contains(
                ProductReadinessStatus.CONFIGURABLE
        )) {
            ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
            if (null == productConfigurationView) {
                productConfigurationView = new ProductConfigurationView(event.getProductId(), event.getActualsAggregationPeriodForTargetForecast(), event.getTargetChangeThresholdForPriceChange(), event.isCrossPriceElasticityConsidered(), event.isAdvertisingExpensesConsidered(), event.getPricingStrategyType(), event.getPricingOptions());
            } else {
                productConfigurationView.setProductId(event.getProductId());
                productConfigurationView.setTargetChangeThresholdForPriceChange(event.getTargetChangeThresholdForPriceChange());
                productConfigurationView.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
                productConfigurationView.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());
                productConfigurationView.setActualsAggregationPeriodForTargetForecast(event.getActualsAggregationPeriodForTargetForecast());
                productConfigurationView.setPricingOptions(event.getPricingOptions());
                productConfigurationView.setPricingStrategyType(event.getPricingStrategyType());
            }
            productConfigurationViewRepository.save(productConfigurationView);
            final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
            if (productActivationStatusView == null) {
                throw ProductNotFoundException.build(event.getProductId());
            }
            productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_CONFIGURED);
            productActivationStatusViewRepository.save(productActivationStatusView);
        } else {
            ProductReadinessException.build(event.getProductId(), ProductStatus.PRODUCT_CONFIGURED);
        }
    }
}
