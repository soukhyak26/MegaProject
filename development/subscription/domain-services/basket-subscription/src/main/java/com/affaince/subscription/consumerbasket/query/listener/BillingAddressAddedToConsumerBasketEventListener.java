package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.BillingAddressAddedToConsumerBasketEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.Address;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-08-2015.
 */
@Component
public class BillingAddressAddedToConsumerBasketEventListener {

    private final ConsumerBasketRepository basketRepository;

    @Autowired
    public BillingAddressAddedToConsumerBasketEventListener(ConsumerBasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(BillingAddressAddedToConsumerBasketEvent event) {
        Address address = new Address(
                event.getAddressLine1(),
                event.getAddressLine2(),
                event.getCity(),
                event.getState(),
                event.getCountry(),
                event.getPinCode()
        );
        ConsumerBasketView subscriberView = basketRepository.findOne(event.getBasketId());
        subscriberView.setBillingAddress(address);
        basketRepository.save(subscriberView);
    }
}
