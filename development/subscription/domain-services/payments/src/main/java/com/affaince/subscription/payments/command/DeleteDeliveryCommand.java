package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

public class DeleteDeliveryCommand {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deletionDate;

    public DeleteDeliveryCommand() {
    }

    public DeleteDeliveryCommand(String subscriberId, String subscriptionId, String deliveryId,LocalDate deletionDate) {
        this.subscriberId = subscriberId;
        this.subscriptionId=subscriptionId;
        this.deliveryId = deliveryId;
        this.deletionDate=deletionDate;
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

    public LocalDate getDeletionDate() {
        return deletionDate;
    }
}
