package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.LatestPriceBucket;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.Map;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class ConfirmSubscriptionCommand {
    @TargetAggregateIdentifier
    private final String subscriberId;
    private Map<String, LatestPriceBucket> latestPriceBucketMap;
    private DeliveryChargesRule deliveryChargesRule;

    public ConfirmSubscriptionCommand(String subscriberId, Map<String, LatestPriceBucket> latestPriceBucketMap, DeliveryChargesRule deliveryChargesRule) {
        this.subscriberId = subscriberId;
        this.latestPriceBucketMap = latestPriceBucketMap;
        this.deliveryChargesRule = deliveryChargesRule;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public Map<String, LatestPriceBucket> getLatestPriceBucketMap() {
        return latestPriceBucketMap;
    }

    public DeliveryChargesRule getDeliveryChargesRule() {
        return deliveryChargesRule;
    }
}
