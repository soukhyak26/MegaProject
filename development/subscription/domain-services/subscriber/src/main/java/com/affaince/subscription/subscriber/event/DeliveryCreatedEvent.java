package com.affaince.subscription.subscriber.event;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.subscriber.domain.DeliveryItem;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class DeliveryCreatedEvent {
    public String deliveryId;
    private String subscriberId;
    private String subscriptionId;
    private int deliverySequence;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private double deliveryWeightInGrms;
    private double rewardPoints;

    public DeliveryCreatedEvent(String deliveryId, String subscriberId, String subscriptionId,int deliverySequence, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status, double deliveryWeightInGrms, double rewardPoints) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliverySequence=deliverySequence;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
        this.deliveryWeightInGrms = deliveryWeightInGrms;
        this.rewardPoints = rewardPoints;
    }

    public DeliveryCreatedEvent() {
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<DeliveryItem> getDeliveryItems() {
        return deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public double getDeliveryWeightInGrms() {
        return deliveryWeightInGrms;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }
}
