package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.DeliveryCostAccountDebitedEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class DeliveryCostAccountDebitedEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;

    @Autowired
    public DeliveryCostAccountDebitedEventListener(DeliveryCostViewRepository deliveryCostViewRepository) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
    }

    @EventHandler
    public void on(DeliveryCostAccountDebitedEvent event) {
        DeliveryCostView deliveryCostView = deliveryCostViewRepository.findById(event.getId());
        deliveryCostView.getDeliveryCostAccount().debit(event.getAmountToDebit());
        deliveryCostViewRepository.save(deliveryCostView);
    }
}
