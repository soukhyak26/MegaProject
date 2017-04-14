package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.subscriber.command.event.DeliveryDeletedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 24-10-2016.
 */
@Component
public class DeliveryDeletedEventListener {

    private final DeliveryViewRepository deliveryViewRepository;

    @Autowired
    public DeliveryDeletedEventListener(DeliveryViewRepository deliveryViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
    }

    @EventHandler
    public void on(DeliveryDeletedEvent event) {
        final DeliveryId deliveryId = new DeliveryId(event.getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId());
        deliveryViewRepository.delete(deliveryId);
    }
}
