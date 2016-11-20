package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.DeliveryInitiatedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class DeliveryInitiatedEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;

    @Autowired
    public DeliveryInitiatedEventListener(DeliveryCostViewRepository deliveryCostViewRepository) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
    }

    @EventHandler
    public void on(DeliveryInitiatedEvent event) {
        DeliveryCostView deliveryCostView = deliveryCostViewRepository.findByDeliveryId(event.getDeliveryId());
        if(deliveryCostView == null) {
            deliveryCostView = new DeliveryCostView(event.getDeliveryId(), event.getSubscriptionId(), 0);
        }
        /*else {
            get delivery cost from event and update
            //TODO: check if this should happen in this event or other
        }*/
        deliveryCostViewRepository.save(deliveryCostView);
    }
}
