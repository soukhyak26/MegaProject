package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.subscriber.event.DeliveryPreparedForDispatchEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryViewRepository;
import com.affaince.subscription.subscriber.query.repository.LatestPriceBucketViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryView;
import com.affaince.subscription.subscriber.query.view.LatestPriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
@Component
public class DeliveryPreparedForDispatchEventListener {

    private final DeliveryViewRepository deliveryViewRepository;
    private final LatestPriceBucketViewRepository latestPriceBucketViewRepository;

    @Autowired
    public DeliveryPreparedForDispatchEventListener(DeliveryViewRepository deliveryViewRepository, LatestPriceBucketViewRepository latestPriceBucketViewRepository) {
        this.deliveryViewRepository = deliveryViewRepository;
        this.latestPriceBucketViewRepository = latestPriceBucketViewRepository;
    }

    @EventHandler
    public void on(DeliveryPreparedForDispatchEvent event) {
        final DeliveryId deliveryId = new DeliveryId(event.getDelivery().getDeliveryId(), event.getSubscriberId(), event.getSubscriptionId());
        final DeliveryView deliveryView = deliveryViewRepository.findOne(deliveryId);
        deliveryView.setStatus(DeliveryStatus.READYFORDELIVERY);
        deliveryView.getDeliveryItems().forEach(deliveryItem -> {
            LatestPriceBucketView latestPriceBucketView = latestPriceBucketViewRepository
                    .findOne(deliveryItem.getDeliveryItemId());
            deliveryItem.setPriceBucketId(latestPriceBucketView.getPriceBucketId());
            deliveryItem.setOfferedPricePerUnit(latestPriceBucketView.getOfferedPricePerUnit());
        });
        deliveryViewRepository.save(deliveryView);
    }
}
