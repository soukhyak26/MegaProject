package com.affaince.subscription.configuration;


import org.axonframework.amqp.eventhandling.RoutingKeyResolver;
import org.axonframework.eventhandling.EventMessage;

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
