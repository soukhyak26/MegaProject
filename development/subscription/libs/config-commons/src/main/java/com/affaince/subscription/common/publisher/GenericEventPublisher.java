package com.affaince.subscription.common.publisher;

import org.axonframework.domain.EventMessage;
import org.axonframework.domain.GenericEventMessage;
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
        System.out.println("@@@Inside GenericEventPublisher:::"+ genericEvent.getClass());
        final EventMessage<Object> eventMessage = GenericEventMessage.asEventMessage(genericEvent);
        eventTemplate.publishEvent(eventMessage);
        System.out.println("@@@Event published successfully: " + genericEvent);
    }
}
