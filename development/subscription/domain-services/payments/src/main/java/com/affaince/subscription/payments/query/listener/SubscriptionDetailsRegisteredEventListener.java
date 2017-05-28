package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.SubscriptionDetailsRegisteredEvent;
import com.affaince.subscription.payments.query.repository.DeliveryDetailsViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.vo.DeliveryDetails;
import com.affaince.subscription.payments.vo.SubscriptionwiseDeliveryId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/18/2017.
 */
@Component
public class SubscriptionDetailsRegisteredEventListener {
    private DeliveryDetailsViewRepository deliveryDetailsViewRepository;
    @Autowired
    public SubscriptionDetailsRegisteredEventListener(DeliveryDetailsViewRepository deliveryDetailsViewRepository) {
        this.deliveryDetailsViewRepository = deliveryDetailsViewRepository;
    }

    @EventHandler
    public void on(SubscriptionDetailsRegisteredEvent event) {
        List<DeliveryDetails> deliveries=event.getDeliveries();
        for(DeliveryDetails delivery: deliveries) {
            DeliveryDetailsView deliveryDetailsView = new DeliveryDetailsView(new SubscriptionwiseDeliveryId(delivery.getSubscriptionId(), delivery.getDeliveryId()), delivery.getDeliveredProductDetails(), delivery.getDeliveryDate());
            deliveryDetailsViewRepository.save(deliveryDetailsView);
        }
    }
}
