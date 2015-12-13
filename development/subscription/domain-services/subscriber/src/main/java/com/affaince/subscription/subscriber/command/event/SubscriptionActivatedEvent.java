package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class SubscriptionActivatedEvent {
    private String basketId;

    public SubscriptionActivatedEvent(String basketId) {
        this.basketId = basketId;
    }

    public SubscriptionActivatedEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }
}
