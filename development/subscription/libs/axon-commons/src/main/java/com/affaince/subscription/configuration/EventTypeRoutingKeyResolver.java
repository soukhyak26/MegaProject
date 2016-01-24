package com.affaince.subscription.configuration;

import org.axonframework.domain.EventMessage;
import org.axonframework.eventhandling.amqp.RoutingKeyResolver;

/**
 * Created by rsavaliya on 10/1/16.
 */
public class EventTypeRoutingKeyResolver implements RoutingKeyResolver {
    @Override
    public String resolveRoutingKey(EventMessage eventMessage) {
        System.out.println(eventMessage.getPayloadType().getName());
        return eventMessage.getPayloadType().getName();
    }
}
