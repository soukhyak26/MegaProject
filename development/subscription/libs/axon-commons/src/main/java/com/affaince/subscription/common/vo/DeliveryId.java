package com.affaince.subscription.common.vo;

import java.io.Serializable;

/**
 * Created by rsavaliya on 14/4/17.
 */
public class DeliveryId implements Serializable {

    private String deliveryId;
    private String subscriberId;
    private String subscriptionId;

    public DeliveryId(String deliveryId, String subscriberId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    @Override
    public String toString() {
        return "DeliveryId{" +
                "deliveryId='" + deliveryId + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", subscriptionId='" + subscriptionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryId)) return false;

        DeliveryId that = (DeliveryId) o;

        if (!getDeliveryId().equals(that.getDeliveryId())) return false;
        if (!getSubscriberId().equals(that.getSubscriberId())) return false;
        return getSubscriptionId().equals(that.getSubscriptionId());
    }

    @Override
    public int hashCode() {
        int result = getDeliveryId().hashCode();
        result = 31 * result + getSubscriberId().hashCode();
        result = 31 * result + getSubscriptionId().hashCode();
        return result;
    }
}
