package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.subscriber.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedEventListener {

    private final DeliveryViewRepository deliveryViewRepository;

    @Autowired
    public DeliveryStatusAndDispatchDateUpdatedEventListener(DeliveryViewRepository deliveryViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
    }

    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) {
        final DeliveryId deliveryId = new DeliveryId(event.getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId());
        DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId());
            deliveryItem = deliveryView.getDeliveryItems().get(deliveryView.getDeliveryItems().indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(itemDispatchStatus.getItemDeliveryStatus());
        }
        deliveryView.setDispatchDate(event.getDispatchDate());
        deliveryView.setStatus(event.getDeliveryStatus());
        deliveryView.setReasonCode(event.getReasonCode());
        deliveryViewRepository.save(deliveryView);
    }
}
