package com.affaince.subscription.integration.command.processor;

import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by mandark on 18-07-2015.
 */
public class SubscriptionableItemReceivedEventProcessor implements ItemProcessor<SubscriptionableItemReceivedEvent, SubscriptionableItemReceivedEvent> {

    @Override
    public SubscriptionableItemReceivedEvent process(SubscriptionableItemReceivedEvent subscriptionableItemReceivedEvent) throws Exception {
        return subscriptionableItemReceivedEvent;
    }
}
