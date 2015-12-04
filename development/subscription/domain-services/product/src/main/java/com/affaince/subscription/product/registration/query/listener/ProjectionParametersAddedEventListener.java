package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.AddProjectionParametersEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.ProjectionParameters;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class ProjectionParametersAddedEventListener {

    private final ProductRepository itemRepository;

    @Autowired
    public ProjectionParametersAddedEventListener(ProductRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on(AddProjectionParametersEvent event) {
        ProjectionParameters projectionParameters = new ProjectionParameters(
                event.getTargetConsumptionPeriod(),
                event.getTargetSalePerConsumptionPeriod(),
                event.getMinimumProfitMargin(),
                event.getMaximumProfitMargin(),
                event.getDemandToSupplyRatio(),
                event.getConsumptionFrequency()
        );
        ProductView productView = itemRepository.findOneByItemId(event.getItemId());
        productView.setProjectionParameters(projectionParameters);
        itemRepository.save(productView);
    }
}
