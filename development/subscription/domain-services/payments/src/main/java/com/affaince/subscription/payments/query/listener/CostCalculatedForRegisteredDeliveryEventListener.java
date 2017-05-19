package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.CostCalculatedForRegisteredDeliveryEvent;
import com.affaince.subscription.payments.query.repository.DeliveryCostViewRepository;
import com.affaince.subscription.payments.query.view.DeliveryCostView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CostCalculatedForRegisteredDeliveryEventListener {
    private final DeliveryCostViewRepository deliveryCostViewRepository;

    @Autowired
    public CostCalculatedForRegisteredDeliveryEventListener(DeliveryCostViewRepository deliveryCostViewRepository) {
        this.deliveryCostViewRepository = deliveryCostViewRepository;
    }

    @EventHandler
    public void on(CostCalculatedForRegisteredDeliveryEvent event) {
        DeliveryCostView deliveryCostView = deliveryCostViewRepository.findByDeliveryId(event.getDeliveryId());
        if(deliveryCostView == null) {
            //TODO:Presently delivery sequence is hardcoded just as a placeholder. It should come from Delivery AggregateEvent coming from subscriber domain
            deliveryCostView = new DeliveryCostView(event.getDeliveryId(), event.getSubscriptionId(),1, event.getTotalDeliveryCost(),event.getDeliveryDate());
        }
        deliveryCostViewRepository.save(deliveryCostView);
    }
}
