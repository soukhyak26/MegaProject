package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.subscriber.command.event.DeliveryCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryItem;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        List<DeliveryItem> deliveryItems = new ArrayList<>();
        event.getDeliveryItems().forEach(deliveryItem -> deliveryItems.add(
                new DeliveryItem(deliveryItem.getDeliveryItemId(), deliveryItem.getDeliveryStatus(),
                        deliveryItem.getWeightInGrms(), deliveryItem.getDeliveryCharges(),
                        deliveryItem.getPriceBucketId(), deliveryItem.getOfferedPricePerUnit(),
                        deliveryItem.getProductPricingCategory())
        ));
        final DeliveryId deliveryId = new DeliveryId(event.getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId());
        final DeliveryView deliveryView = new DeliveryView(deliveryId,deliveryItems , event.getDeliveryDate(), event.getStatus(), event.getRewardPoints());
        deliveryViewRepository.save(deliveryView);
    }
}
