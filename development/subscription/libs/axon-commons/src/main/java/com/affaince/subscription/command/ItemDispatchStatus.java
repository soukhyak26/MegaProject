package com.affaince.subscription.command;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by rbsavaliya on 04-10-2015.
 */
public class ItemDispatchStatus {
    private String itemId;
    private DeliveryStatus itemDeliveryStatus;
    private String priceBucketId;

    public ItemDispatchStatus(String itemId, DeliveryStatus itemDeliveryStatus) {
        this.itemId = itemId;
        this.itemDeliveryStatus = itemDeliveryStatus;
    }

    public ItemDispatchStatus() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public DeliveryStatus getItemDeliveryStatus() {
        return itemDeliveryStatus;
    }

    public void setItemDeliveryStatus(DeliveryStatus itemDeliveryStatus) {
        this.itemDeliveryStatus = itemDeliveryStatus;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemDispatchStatus that = (ItemDispatchStatus) o;

        return itemId.equals(that.itemId);

    }

    @Override
    public int hashCode() {
        return itemId.hashCode();
    }
}
