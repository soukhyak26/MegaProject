package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.common.vo.Address;
import com.affaince.subscription.subscriber.event.BillingAddressAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
@Component
public class BillingAddressAddedToSubscriptionEventListener {

    private final SubscriptionViewRepository basketRepository;

    @Autowired
    public BillingAddressAddedToSubscriptionEventListener(SubscriptionViewRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(BillingAddressAddedToSubscriptionEvent event) {
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        SubscriptionView subscriberView = basketRepository.findOne(event.getSubscriptionId());
        subscriberView.setBillingAddress(address);
        basketRepository.save(subscriberView);
    }
}