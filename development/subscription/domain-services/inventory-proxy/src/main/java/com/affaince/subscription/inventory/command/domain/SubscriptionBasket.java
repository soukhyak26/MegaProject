package com.affaince.subscription.inventory.command.domain;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;

import java.util.List;

/**
 * Created by mandark on 22-08-2015.
 */
public class SubscriptionBasket extends AbstractAnnotatedAggregateRoot{
    private String basketId;
    private String subscriberId;
    private List<String> subscriptionableItemIds;
    private SubscriptionBasketStatus subscriptionBasketStatus;
    private double totalBasketPrice;

    public String getBasketId() {
        return this.basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public List<String> getSubscriptionableItemIds() {
        return this.subscriptionableItemIds;
    }

    public void setSubscriptionableItemIds(List<String> subscriptionableItemIds) {
        this.subscriptionableItemIds = subscriptionableItemIds;
    }

    public SubscriptionBasketStatus getSubscriptionBasketStatus() {
        return this.subscriptionBasketStatus;
    }

    public void setSubscriptionBasketStatus(SubscriptionBasketStatus subscriptionBasketStatus) {
        this.subscriptionBasketStatus = subscriptionBasketStatus;
    }

    public double getTotalBasketPrice() {
        return this.totalBasketPrice;
    }

    public void setTotalBasketPrice(double totalBasketPrice) {
        this.totalBasketPrice = totalBasketPrice;
    }
}
