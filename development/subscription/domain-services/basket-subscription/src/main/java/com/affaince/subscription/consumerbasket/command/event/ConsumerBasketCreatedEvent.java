package com.affaince.subscription.consumerbasket.command.event;

import com.affaince.subscription.common.type.ConsumerBasketActivationStatus;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public class ConsumerBasketCreatedEvent {

    private String basketId;
    private String subscriberId;
    private LocalDate basketCreatedDate;
    private LocalDate basketExpiredDate;
    private ConsumerBasketActivationStatus consumerBasketStatus;

    public ConsumerBasketCreatedEvent(String basketId, String subscriberId, LocalDate basketCreatedDate, LocalDate basketExpiredDate, ConsumerBasketActivationStatus consumerBasketStatus) {
        this.basketId = basketId;
        this.subscriberId = subscriberId;
        this.basketCreatedDate = basketCreatedDate;
        this.basketExpiredDate = basketExpiredDate;
        this.consumerBasketStatus = consumerBasketStatus;
    }

    public ConsumerBasketCreatedEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
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
