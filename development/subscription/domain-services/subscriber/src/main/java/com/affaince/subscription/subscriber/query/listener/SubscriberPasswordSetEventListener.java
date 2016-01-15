package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriberPasswordSetEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 26-12-2015.
 */
@Component
public class SubscriberPasswordSetEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public SubscriberPasswordSetEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(SubscriberPasswordSetEvent event) {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(event.getSubscriberId());
        subscriberView.setPassword(event.getPassword());
        subscriberViewRepository.save(subscriberView);
    }
}
