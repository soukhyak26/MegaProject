package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.subscriber.command.domain.DeliveryItem;
import com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-10-2015.
 */
@Component
public class StatusAndDispatchDateUpdatedEventListener {

    private final DeliveryViewRepository deliveryViewRepository;

    @Autowired
    public StatusAndDispatchDateUpdatedEventListener(DeliveryViewRepository deliveryViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
    }

    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) {
        DeliveryView deliveryView = deliveryViewRepository.findOne(event.getBasketId());
        for (ItemDispatchStatus itemDispatchStatus : event.getItemDispatchStatuses()) {
            DeliveryItem deliveryItem = new DeliveryItem(itemDispatchStatus.getItemId());
            deliveryItem = deliveryView.getDeliveryItems().get(deliveryView.getDeliveryItems().indexOf(deliveryItem));
            deliveryItem.setDeliveryStatus(DeliveryStatus.valueOf(itemDispatchStatus.getItemDeliveryStatus()));
        }
        deliveryView.setDispatchDate(new LocalDate(event.getDispatchDate()));
        deliveryView.setStatus(DeliveryStatus.valueOf(event.getBasketDeliveryStatus()));
        deliveryViewRepository.save(deliveryView);
    }
}
