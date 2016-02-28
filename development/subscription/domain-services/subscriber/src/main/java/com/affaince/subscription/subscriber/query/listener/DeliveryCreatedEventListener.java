package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.DeliveryCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Component
public class DeliveryCreatedEventListener {
    private final DeliveryViewRepository deliveryViewRepository;

    @Autowired
    public DeliveryCreatedEventListener(DeliveryViewRepository deliveryViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
    }

    @EventHandler
    public void on(DeliveryCreatedEvent event) {
        final DeliveryView deliveryView = new DeliveryView(event.getDeliveryId(), event.getSubscriberId(),
                event.getSubscriptionId(), event.getDeliveryItems(), event.getDeliveryDate(), event.getStatus());
        deliveryViewRepository.save(deliveryView);
    }
}
