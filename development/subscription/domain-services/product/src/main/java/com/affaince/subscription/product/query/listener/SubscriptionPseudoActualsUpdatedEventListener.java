package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.SubscriptionPseudoActualsUpdatedEvent;
import com.affaince.subscription.product.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 14-08-2016.
 */
@Component
public class SubscriptionPseudoActualsUpdatedEventListener {

    private final ProductPseudoActualsViewRepository productPseudoActualsViewRepository;

    @Autowired
    public SubscriptionPseudoActualsUpdatedEventListener(ProductPseudoActualsViewRepository productPseudoActualsViewRepository) {
        this.productPseudoActualsViewRepository = productPseudoActualsViewRepository;
    }

    @EventHandler
    public void on(SubscriptionPseudoActualsUpdatedEvent event) {
        List<ProductPseudoActualsView> earlierPseudoActualsWithOverlappingPeriods = productPseudoActualsViewRepository.findByProductVersionId_ProductIdAndForecastContentStatusAndForecastDateLessThan(event.getProductId(), ForecastContentStatus.ACTIVE, event.getForecastDate());
        for (ProductPseudoActualsView earlierView : earlierPseudoActualsWithOverlappingPeriods) {
            earlierView.setForecastContentStatus(ForecastContentStatus.EXPIRED);
        }
        if(null !=earlierPseudoActualsWithOverlappingPeriods && earlierPseudoActualsWithOverlappingPeriods.size()>0){
            productPseudoActualsViewRepository.save(earlierPseudoActualsWithOverlappingPeriods);
        }
        final ProductPseudoActualsView productPseudoActualsView = new ProductPseudoActualsView(
                new ForecastVersionId(event.getProductId(), event.getForecastStartDate(),event.getForecastDate()),
                event.getForecastEndDate(),
                event.getNewSubscriptionForecast(),
                event.getChurnedSubscriptionForecast(),
                event.getForecastedTotalSubscriptionCount()
        );
        productPseudoActualsViewRepository.save(productPseudoActualsView);
    }
}
