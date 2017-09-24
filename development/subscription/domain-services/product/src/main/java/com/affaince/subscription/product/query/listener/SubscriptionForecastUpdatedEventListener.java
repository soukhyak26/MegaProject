package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 06-07-2016.
 */
@Component
public class SubscriptionForecastUpdatedEventListener {
    private final ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    public SubscriptionForecastUpdatedEventListener(ProductForecastViewRepository productForecastViewRepository, ProductConfigurationViewRepository productConfigurationViewRepository) {
        this.productForecastViewRepository = productForecastViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastUpdatedEvent event) {
        List<ProductForecastView> earlierForecastsWithOverlappingPeriods = productForecastViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusAndForecastDateLessThan(event.getProductId(), ForecastContentStatus.ACTIVE, event.getForecastDate());
        for (ProductForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size()>0){
            productForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
        //create and save new latest forecast
        final ProductForecastView productForecastView = new ProductForecastView(
                new ForecastVersionId(event.getProductId(), event.getForecastStartDate(), event.getForecastDate()),
                event.getForecastEndDate(),
                event.getNewSubscriptionForecast(),
                event.getChurnedSubscriptionForecast(),
                event.getForecastedTotalSubscriptionCount());
        productForecastViewRepository.save(productForecastView);
    }
}
