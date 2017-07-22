package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

import java.util.Map;

public class DeliveryPriceCalculatedEvent {
    private String subscriptionId;
    private Map<LocalDate, Double> deliveryWisePriceMap;

    public DeliveryPriceCalculatedEvent(String subscriptionId, Map<LocalDate, Double> deliveryWisePriceMap) {
        this.subscriptionId = subscriptionId;
        this.deliveryWisePriceMap = deliveryWisePriceMap;
    }

    public DeliveryPriceCalculatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Map<LocalDate, Double> getDeliveryWisePriceMap() {
        return deliveryWisePriceMap;
    }
}
