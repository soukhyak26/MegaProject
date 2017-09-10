package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriptionForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionForecastViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriptionForecastCreatedEventListener {

    private final SubscriptionForecastViewRepository subscriptionForecastViewRepository;

    public SubscriptionForecastCreatedEventListener(SubscriptionForecastViewRepository subscriptionForecastViewRepository) {
        this.subscriptionForecastViewRepository = subscriptionForecastViewRepository;
    }

    @EventHandler
    public void on(SubscriptionForecastCreatedEvent event){

    }
}
