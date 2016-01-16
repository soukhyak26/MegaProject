package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriptionSpecificExpenseAddedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionSpecificOperatingExpenseViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionSpecificOperatingExpenseView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Component
public class SubscriptionSpecificExpenseAddedEventListener {

    private final SubscriptionSpecificOperatingExpenseViewRepository operatingExpenseViewRepository;

    @Autowired
    public SubscriptionSpecificExpenseAddedEventListener(SubscriptionSpecificOperatingExpenseViewRepository operatingExpenseViewRepository) {
        this.operatingExpenseViewRepository = operatingExpenseViewRepository;
    }

    @EventHandler
    public void on(SubscriptionSpecificExpenseAddedEvent event) {
        final SubscriptionSpecificOperatingExpenseView subscriptionSpecificOperatingExpenseView = new SubscriptionSpecificOperatingExpenseView(
                event.getId(), event.getExpenseHeader(), event.getDeliveryChargesRules()
        );
        operatingExpenseViewRepository.save(subscriptionSpecificOperatingExpenseView);
    }
}
