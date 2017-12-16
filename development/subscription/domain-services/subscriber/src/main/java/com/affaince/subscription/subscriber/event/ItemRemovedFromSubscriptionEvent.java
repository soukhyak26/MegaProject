package com.affaince.subscription.subscriber.event;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
public class ItemRemovedFromSubscriptionEvent {

    private String subscriptionId;
    private String itemId;

    public ItemRemovedFromSubscriptionEvent(String subscriptionId, String itemId) {
        this.subscriptionId = subscriptionId;
        this.itemId = itemId;
    }

    public ItemRemovedFromSubscriptionEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
