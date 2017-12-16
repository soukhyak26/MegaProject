package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.product.event.NextForecastDateUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 6/24/2017.
 */
@Component
public class NextForecastDateUpdatedEventListener {
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired

    public NextForecastDateUpdatedEventListener(ProductConfigurationViewRepository productConfigurationViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
    }

    public void on(NextForecastDateUpdatedEvent event){
        ProductConfigurationView productConfigurationView=productConfigurationViewRepository.findOne(event.getProductId());
        productConfigurationView.setNextForecastDate(event.getNextForecastDate());
        productConfigurationViewRepository.save(productConfigurationView);
    }
}
