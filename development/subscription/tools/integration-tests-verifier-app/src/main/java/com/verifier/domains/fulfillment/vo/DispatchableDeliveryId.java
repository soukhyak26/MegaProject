package com.verifier.domains.fulfillment.vo;


import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 2/10/2018.
 */
public class DispatchableDeliveryId implements Serializable {
    private String deliveryId;
    private String subscriberId;
    private String subscriptionId;
    private LocalDate deliveryDate;

    public DispatchableDeliveryId(String deliveryId, String subscriberId, String subscriptionId, LocalDate deliveryDate) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryDate = deliveryDate;
    }
    public DispatchableDeliveryId(){}
    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}
