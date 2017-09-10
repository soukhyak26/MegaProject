package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriberForecastCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscribersForecastViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 9/10/2017.
 */
@Component
public class SubscriberForecastCreatedEventListener {
    private final SubscribersForecastViewRepository subscribersForecastViewRepository;

    public SubscriberForecastCreatedEventListener(SubscribersForecastViewRepository subscribersForecastViewRepository) {
        this.subscribersForecastViewRepository = subscribersForecastViewRepository;
    }

    @EventHandler
    public void on(SubscriberForecastCreatedEvent event){

    }
}
