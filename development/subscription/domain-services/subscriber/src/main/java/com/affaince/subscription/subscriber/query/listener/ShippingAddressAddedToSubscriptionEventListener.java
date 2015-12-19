package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.subscriber.command.event.ShippingAddressAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
@Component
public class ShippingAddressAddedToSubscriptionEventListener {

    private final SubscriptionRepository basketRepository;

    @Autowired
    public ShippingAddressAddedToSubscriptionEventListener(SubscriptionRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    private void on(ShippingAddressAddedToSubscriptionEvent event) {
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        SubscriptionView subscriberView = basketRepository.findOne(event.getSubscriptionId());
        subscriberView.setShippingAddress(address);
        basketRepository.save(subscriberView);
    }
}