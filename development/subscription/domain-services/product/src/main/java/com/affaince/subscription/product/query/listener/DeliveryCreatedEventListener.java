package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.DeliveryCreatedEvent;
import com.affaince.subscription.product.query.repository.SubscriptionInfoViewRepository;
import com.affaince.subscription.product.query.view.SubscriptionInfoView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 18/1/16.
 */
@Component
public class DeliveryCreatedEventListener {
    private final SubscriptionInfoViewRepository subscriptionInfoViewRepository;

    @Autowired
    public DeliveryCreatedEventListener(SubscriptionInfoViewRepository subscriptionInfoViewRepository) {
        this.subscriptionInfoViewRepository = subscriptionInfoViewRepository;
    }

    @EventHandler
    public void on(DeliveryCreatedEvent event) {
        final SubscriptionInfoView subscriptionInfoView = new SubscriptionInfoView(event.getDeliveryId(),
                event.getSubscriberId(), event.getSubscriptionId(), event.getDeliveryItems(), event.getDeliveryDate(),
                event.getDeliveryWeightInGrms());
        subscriptionInfoViewRepository.save(subscriptionInfoView);
    }
}
