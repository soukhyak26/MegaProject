package com.affaince.subscription.payments.vo;

import java.io.Serializable;

/**
 * Created by mandar on 5/28/2017.
 */
public class SubscriptionwiseDeliveryId implements Serializable{
    private String deliveryId;
    private String subscriptionId;

    public SubscriptionwiseDeliveryId(String deliveryId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }
}
