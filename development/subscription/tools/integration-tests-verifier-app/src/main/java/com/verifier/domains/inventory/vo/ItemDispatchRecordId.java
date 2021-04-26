package com.verifier.domains.inventory.vo;

import org.joda.time.LocalDate;

import java.io.Serializable;

public class ItemDispatchRecordId implements Serializable {
    private final String productInventoryId;
    private final String subscriberId;
    private final String subscriptionId;
    private final String deliveryId;
    private final LocalDate deliveryDate;
    private final String productId;
    private final String batchId;

    public ItemDispatchRecordId(String productInventoryId, String subscriberId, String subscriptionId, String deliveryId, LocalDate deliveryDate, String productId, String batchId) {
        this.productInventoryId = productInventoryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryId = deliveryId;
        this.deliveryDate = deliveryDate;
        this.productId = productId;
        this.batchId = batchId;
    }

    public String getProductInventoryId() {
        return productInventoryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getBatchId() {
        return batchId;
    }
}
