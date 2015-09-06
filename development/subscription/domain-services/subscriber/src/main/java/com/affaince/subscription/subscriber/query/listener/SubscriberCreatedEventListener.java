package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.NetWorthSubscriberStatus;
import com.affaince.subscription.subscriber.command.event.SubscriberCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class SubscriberCreatedEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public SubscriberCreatedEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(SubscriberCreatedEvent event) {
        SubscriberView subscriberView = new SubscriberView(event.getSubscriberId(),
                event.getSubscriberName(), event.getBillingAddress(), event.getShippingAddress(),
                event.getContactDetails(), NetWorthSubscriberStatus.valueOf(event.getSubscriberStatusCode()), null, 0);
        subscriberViewRepository.save(subscriberView);
    }
}