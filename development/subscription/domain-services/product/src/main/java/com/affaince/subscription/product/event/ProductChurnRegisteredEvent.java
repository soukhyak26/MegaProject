package com.affaince.subscription.product.event;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandar on 23-07-2016.
 */
public class ProductChurnRegisteredEvent {
    @TargetAggregateIdentifier
    private String productId;
    private long churnedSubscriptionCount;
}
