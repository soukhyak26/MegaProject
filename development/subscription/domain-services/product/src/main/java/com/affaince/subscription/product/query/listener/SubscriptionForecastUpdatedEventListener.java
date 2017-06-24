package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.event.SubscriptionForecastUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
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
        List<ProductForecastView> earlierForecastsWithOverlappingPeriods = productForecastViewRepository.findByProductVersionId_ProductIdAndProductForecastStatusAndForecastDateLessThan(event.getProductId(), ProductForecastStatus.ACTIVE, event.getForecastDate());
        for (ProductForecastView earlierView : earlierForecastsWithOverlappingPeriods) {
            earlierView.setProductForecastStatus(ProductForecastStatus.EXPIRED);
        }
        if(null != earlierForecastsWithOverlappingPeriods && earlierForecastsWithOverlappingPeriods.size()>0){
            productForecastViewRepository.save(earlierForecastsWithOverlappingPeriods);
        }
        //create and save new latest forecast
        final ProductForecastView productForecastView = new ProductForecastView(
                new ProductVersionId(event.getProductId(), event.getForecastStartDate()),
                event.getForecastEndDate(),
                event.getNewSubscriptionForecast(),
                event.getChurnedSubscriptionForecast(),
                event.getForecastedTotalSubscriptionCount(), event.getForecastDate());
        productForecastViewRepository.save(productForecastView);
    }
}
