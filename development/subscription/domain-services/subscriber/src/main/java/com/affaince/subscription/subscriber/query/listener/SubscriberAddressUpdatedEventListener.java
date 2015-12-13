package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.subscriber.command.event.SubscriberAddressUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class SubscriberAddressUpdatedEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public SubscriberAddressUpdatedEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(SubscriberAddressUpdatedEvent event) {
        final Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        final SubscriberView subscriberView = subscriberViewRepository.findOne(event.getSubscriberId());
        subscriberView.setAddress(address);
        subscriberViewRepository.save(subscriberView);
    }
}
