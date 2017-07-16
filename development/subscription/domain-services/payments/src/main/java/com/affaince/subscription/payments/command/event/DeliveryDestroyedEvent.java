package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
import org.joda.time.LocalDate;

public class DeliveryDestroyedEvent {
    private String subscriberId;
    private String deliveryId;
    private String subscriptionId;
    private int sequence;
    private double totalDeliveryCost;
    private double paymentReceived;
    private LocalDate deletionDate;
    public DeliveryDestroyedEvent() {
    }

    public DeliveryDestroyedEvent(String subscriberId,String subscriptionId,String deliveryId,int sequence,double totalDeliveryCost,double paymentReceived,LocalDate deletionDate) {
        this.subscriberId=subscriberId;
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
        this.sequence=sequence;
        this.totalDeliveryCost=totalDeliveryCost;
        this.paymentReceived=paymentReceived;
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

    public int getSequence() {
        return sequence;
    }

    public double getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }
}
