package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.ContactDetailsAddedEvent;
import com.affaince.subscription.consumerbasket.query.repository.ConsumerBasketRepository;
import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import com.affaince.subscription.consumerbasket.query.view.ContactDetails;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class ContactDetailsAddedEventListener {

    private final ConsumerBasketRepository consumerBasketRepository;

    @Autowired
    public ContactDetailsAddedEventListener(ConsumerBasketRepository consumerBasketRepository) {
        this.consumerBasketRepository = consumerBasketRepository;
    }

    @EventHandler
    public void on(ContactDetailsAddedEvent event) {
        ConsumerBasketView consumerBasketView = consumerBasketRepository.findOne(event.getBasketId());
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        consumerBasketView.setContactDetails(contactDetails);
        consumerBasketRepository.save(consumerBasketView);
    }
}
