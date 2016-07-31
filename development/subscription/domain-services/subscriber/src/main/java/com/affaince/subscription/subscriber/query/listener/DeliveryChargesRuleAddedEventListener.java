package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.DeliveryChargesRuleAddedEvent;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rsavaliya on 27/2/16.
 */
@Component
public class DeliveryChargesRuleAddedEventListener {

    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;

    @Autowired
    public DeliveryChargesRuleAddedEventListener(DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository) {
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
    }

    @EventHandler
    public void on (DeliveryChargesRuleAddedEvent event) {
        final DeliveryChargesRuleView deliveryChargesRuleView = new DeliveryChargesRuleView(
                event.getRuleId(), event.getDeliveryChargesRules()
        );
        deliveryChargesRuleViewRepository.save(deliveryChargesRuleView);
    }
}
