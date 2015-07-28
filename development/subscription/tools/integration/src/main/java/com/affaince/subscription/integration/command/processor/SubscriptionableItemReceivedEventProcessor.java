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
public class SubscriptionableItemReceivedEventProcessor implements Processor {

    private final GenericEventPublisher eventPublisher;
    @Autowired
    public SubscriptionableItemReceivedEventProcessor(GenericEventPublisher publisher){
        this.eventPublisher= publisher;
    }
    @Override
    public void process(Exchange exchange) {
        System.out.println("@@@@@@@FINALLY INSIDE PROCESSOR@@@@@@@:" + exchange.getIn());
        List<Map<String,Object>> map = (List<Map<String,Object>>)exchange.getIn().getBody();
        SubscriptionableItemReceivedEvent event=(SubscriptionableItemReceivedEvent)map.get(0).get(SubscriptionableItemReceivedEvent.class.getCanonicalName());
        System.out.println("@@@@@The Event is:" + event);
                eventPublisher.publish(event);
    }
}
