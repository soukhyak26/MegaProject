package com.affaince.subscription.payments.command;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.payments.vo.DeliveryItem;
import org.joda.time.LocalDate;

import java.util.List;

public class CreateDeliveryCommand {
    private String deliveryId;
    private String subscriberId;
    private String subscriptionId;
    private int sequence;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private double deliveryWeightInGrms;
    private double rewardPoints;
    private LocalDate deliveryCreationDate;

    public CreateDeliveryCommand(String deliveryId, String subscriberId, String subscriptionId,int sequence, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status, double deliveryWeightInGrms,double rewardPoints,LocalDate deliveryCreationDate ) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.sequence=sequence;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
        this.deliveryWeightInGrms = deliveryWeightInGrms;
        this.rewardPoints=rewardPoints;
        this.deliveryCreationDate=deliveryCreationDate;
    }

    public CreateDeliveryCommand() {
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

    public int getSequence() {
        return sequence;
    }

    public LocalDate getDeliveryCreationDate() {
        return deliveryCreationDate;
    }
}
