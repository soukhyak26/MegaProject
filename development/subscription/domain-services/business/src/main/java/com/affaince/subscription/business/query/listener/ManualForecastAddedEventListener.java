package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.business.command.event.ManualForecastAddedEvent;
import com.affaince.subscription.business.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 11-11-2016.
 */
@Component
public class ManualForecastAddedEventListener {


    private ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    public ManualForecastAddedEventListener(ProductForecastViewRepository productForecastViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
    }


    @EventHandler
    public void on(ManualForecastAddedEvent event) {
        final ProductForecastView productForecastView = new ProductForecastView(
                new ProductVersionId(event.getProductId(), event.getForecastStartDate()),
                event.getForecastEndDate(),
                event.getNumberOfNewSubscriptions(),
                event.getNumberOfChurnedSubscriptions(),
                event.getNumberOfTotalSubscriptions());

        productForecastViewRepository.save(productForecastView);


    }
}
