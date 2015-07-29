package com.affaince.subscription.integration.command.processor;

import com.affaince.subscription.integration.command.event.GenericEventPublisher;
import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 18-07-2015.
 */
public class SubscriptionableItemReceivedEventProcessor {

    private final GenericEventPublisher eventPublisher;
    @Autowired
    public SubscriptionableItemReceivedEventProcessor(GenericEventPublisher publisher){
        this.eventPublisher= publisher;
    }

    public Object process(Map<String,Object> map) {
        System.out.println("@@@@@@@FINALLY INSIDE PROCESSOR@@@@@@@:");
        Object event=map.values().toArray()[0];
        System.out.println("@@@@@The Event is:" + event);
                //eventPublisher.publish(event);
         return event;
    }
}
