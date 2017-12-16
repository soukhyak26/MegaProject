package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.affaince.subscription.payments.event.DeliveryDispatchApprovalSentEvent;
import com.affaince.subscription.payments.query.repository.DeliveryDetailsViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/11/2017.
 */
@Component
public class DeliveryDispatchApprovalSentEventListener {

    private final DeliveryDetailsViewRepository deliveryDetailsViewRepository;
    @Autowired
    public DeliveryDispatchApprovalSentEventListener(DeliveryDetailsViewRepository deliveryDetailsViewRepository) {
        this.deliveryDetailsViewRepository = deliveryDetailsViewRepository;
    }

    @EventHandler
    public void on(DeliveryDispatchApprovalSentEvent event){
        DeliveryDetailsView deliveryDetailsView=deliveryDetailsViewRepository.findOne(new DeliveryId(event.getSubscriberId(),event.getSubscriptionId(),event.getDeliveryId()));
        deliveryDetailsView.setDeliveryStatus(event.isValidateForDispatchFlag()? DeliveryStatus.DELIVERED:DeliveryStatus.HALTED);
        deliveryDetailsViewRepository.save(deliveryDetailsView);
    }
}
