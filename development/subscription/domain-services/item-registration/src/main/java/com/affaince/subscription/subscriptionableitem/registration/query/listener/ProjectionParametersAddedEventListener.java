package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.AddProjectionParametersEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.ProjectionParameters;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
@Component
public class ProjectionParametersAddedEventListener {

    private SubscriptionableItemRepository itemRepository;

    @Autowired
    public ProjectionParametersAddedEventListener (SubscriptionableItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @EventHandler
    public void on (AddProjectionParametersEvent event) {
        ProjectionParameters projectionParameters = new ProjectionParameters(
                event.getTargetConsumptionPeriod(),
                event.getTargetConsumptionPeriodUnit(),
                event.getTargetSalePerConsumptionPeriod(),
                event.getMinimumProfitMargin(),
                event.getMaximumProfitMargin(),
                event.getDemandToSupplyRatio(),
                event.getConsumptionFrequency()
        );
        SubscriptionableItemView subscriptionableItemView = itemRepository.findOneByItemId(event.getItemId());
        subscriptionableItemView.setProjectionParameters(projectionParameters);
        itemRepository.save(subscriptionableItemView);
    }
}
