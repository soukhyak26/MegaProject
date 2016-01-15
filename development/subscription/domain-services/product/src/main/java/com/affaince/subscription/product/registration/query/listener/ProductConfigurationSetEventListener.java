package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.ProductConfigurationSetEvent;
import com.affaince.subscription.product.registration.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductConfigurationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
@Component
public class ProductConfigurationSetEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;

    @Autowired
    public ProductConfigurationSetEventListener(ProductConfigurationViewRepository productConfigurationViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
    }

    public void on(ProductConfigurationSetEvent event) {
        final ProductConfigurationView productConfigurationView = new ProductConfigurationView();
        productConfigurationView.setProductId(event.getProductId());
        productConfigurationView.setDemandCurvePeriod(event.getDemandCurvePeriod());
        productConfigurationView.setRevenueChangeThresholdForPriceChange(event.getRevenueChangeThresholdForPriceChange());
        productConfigurationView.setCrossPriceElasticityConsidered(event.isCrossPriceElasticityConsidered());
        productConfigurationView.setAdvertisingExpensesConsidered(event.isAdvertisingExpensesConsidered());

        productConfigurationViewRepository.save(productConfigurationView);
    }
}
