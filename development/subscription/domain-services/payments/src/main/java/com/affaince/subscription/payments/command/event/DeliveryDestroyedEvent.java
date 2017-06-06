package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
import org.joda.time.LocalDate;

public class DeliveryDestroyedEvent {
    private String subscriberId;
    private String deliveryId;
    private String subscriptionId;
    private LocalDate deletionDate;
    public DeliveryDestroyedEvent() {
    }

    public DeliveryDestroyedEvent(String subscriberId,String subscriptionId,String deliveryId,LocalDate deletionDate) {
        this.subscriberId=subscriberId;
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
        this.deletionDate=deletionDate;

    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public LocalDate getDeletionDate() {
        return deletionDate;
    }
}
