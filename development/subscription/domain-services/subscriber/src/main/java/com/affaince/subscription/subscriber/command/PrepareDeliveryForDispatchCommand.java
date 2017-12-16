package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.domain.LatestPriceBucket;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.Map;

/**
 * Created by rbsavaliya on 11-12-2016.
 */
public class PrepareDeliveryForDispatchCommand {
    @AggregateIdentifier
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    Map<String, LatestPriceBucket> latestPriceBucketMap;

    public PrepareDeliveryForDispatchCommand(String subscriberId, String subscriptionId, String deliveryId, Map<String, LatestPriceBucket> latestPriceBucketMap) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.latestPriceBucketMap = latestPriceBucketMap;
    }

    public PrepareDeliveryForDispatchCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public Map<String, LatestPriceBucket> getLatestPriceBucketMap() {
        return latestPriceBucketMap;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
