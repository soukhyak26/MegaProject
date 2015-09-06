package com.affaince.subscription.integration.command.event;

import org.axonframework.eventhandling.EventTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 28-07-2015.
 */
@Component
public class GenericEventPublisher {
    private final EventTemplate eventTemplate;

    @Autowired
    public GenericEventPublisher(EventTemplate template) {
        eventTemplate = template;
    }

    public void publish(Object genericEvent) {
        eventTemplate.publishEvent(genericEvent);
        System.out.println("@@@Event published successfully: " + genericEvent);
    }
}
