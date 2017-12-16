package com.affaince.subscription.product.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandar on 23-07-2016.
 */
public class ProductSubscriptionRegisteredEvent {
    @TargetAggregateIdentifier
    String productId;
    long subscriptionCount;

}
