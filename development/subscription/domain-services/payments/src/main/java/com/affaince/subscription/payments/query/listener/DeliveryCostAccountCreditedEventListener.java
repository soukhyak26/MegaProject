package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.DeliveryCostAccountCreditedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class DeliveryCostAccountCreditedEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;

    @Autowired
    public DeliveryCostAccountCreditedEventListener(DeliveryCostViewRepository deliveryCostViewRepository) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
    }

    @EventHandler
    public void on(DeliveryCostAccountCreditedEvent event) {
        DeliveryCostView deliveryCostView = deliveryCostViewRepository.findByDeliveryId(event.getId());
        deliveryCostView.getDeliveryCostAccount().credit(event.getAmountToCredit());
        deliveryCostViewRepository.save(deliveryCostView);
    }
}
