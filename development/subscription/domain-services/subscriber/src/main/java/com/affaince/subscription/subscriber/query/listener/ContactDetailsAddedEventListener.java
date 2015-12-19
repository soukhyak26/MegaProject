package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.subscriber.command.event.ContactDetailsAddedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class ContactDetailsAddedEventListener {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public ContactDetailsAddedEventListener(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @EventHandler
    public void on(ContactDetailsAddedEvent event) {
        SubscriptionView subscriptionView = subscriptionRepository.findOne(event.getSubscriptionId());
        ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        subscriptionView.setContactDetails(contactDetails);
        subscriptionRepository.save(subscriptionView);
    }
}