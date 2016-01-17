package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.command.event.BasketDeletedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-10-2015.
 */
@Component
public class BasketDeletedEventListener {

    private final DeliveryViewRepository deliveryViewRepository;

    @Autowired
    public BasketDeletedEventListener(DeliveryViewRepository deliveryViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
    }

    @EventHandler
    public void on(BasketDeletedEvent event) {
        final DeliveryView deliveryView = deliveryViewRepository.findOne(event.getBasketId());
        deliveryView.setStatus(DeliveryStatus.DELETED);
        deliveryViewRepository.save(deliveryView);
    }
}
