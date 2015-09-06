package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.vo.ContactDetails;
import com.affaince.subscription.subscriber.command.event.SubscriberContactDetailsUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriberViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriberView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 02-08-2015.
 */
@Component
public class SubscriberContactDetailsUpdatedEventListener {

    private final SubscriberViewRepository subscriberViewRepository;

    @Autowired
    public SubscriberContactDetailsUpdatedEventListener(SubscriberViewRepository subscriberViewRepository) {
        this.subscriberViewRepository = subscriberViewRepository;
    }

    @EventHandler
    public void on(SubscriberContactDetailsUpdatedEvent event) {
        final SubscriberView subscriberView = subscriberViewRepository.findOne(event.getSubscriberId());
        final ContactDetails contactDetails = new ContactDetails(
                event.getEmail(),
                event.getMobileNumber(),
                event.getAlternativeNumber()
        );
        subscriberView.setContactDetails(contactDetails);
        subscriberViewRepository.save(subscriberView);
    }
}
