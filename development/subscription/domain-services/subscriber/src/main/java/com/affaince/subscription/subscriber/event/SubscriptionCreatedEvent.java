package com.affaince.subscription.subscriber.event;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class SubscriptionCreatedEvent {

    private String subscriptionId;
    private String subscriberId;
    private LocalDate basketCreatedDate;
    private LocalDate basketExpiredDate;
    private ConsumerBasketActivationStatus consumerBasketStatus;

    public SubscriptionCreatedEvent(String subscriptionId, String subscriberId, LocalDate basketCreatedDate, LocalDate basketExpiredDate, ConsumerBasketActivationStatus consumerBasketStatus) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.basketCreatedDate = basketCreatedDate;
        this.basketExpiredDate = basketExpiredDate;
        this.consumerBasketStatus = consumerBasketStatus;
    }

    public SubscriptionCreatedEvent() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public LocalDate getBasketCreatedDate() {
        return basketCreatedDate;
    }

    public void setBasketCreatedDate(LocalDate basketCreatedDate) {
        this.basketCreatedDate = basketCreatedDate;
    }

    public LocalDate getBasketExpiredDate() {
        return basketExpiredDate;
    }

    public void setBasketExpiredDate(LocalDate basketExpiredDate) {
        this.basketExpiredDate = basketExpiredDate;
    }

    public ConsumerBasketActivationStatus getConsumerBasketStatus() {
        return consumerBasketStatus;
    }

    public void setConsumerBasketStatus(ConsumerBasketActivationStatus consumerBasketStatus) {
        this.consumerBasketStatus = consumerBasketStatus;
    }
}
