package com.affaince.subscription.payments.event;

import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 5/17/2017.
 */
public class NewDeliveryRegisteredEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private int sequence;
    private DeliveryDetails deliveryDetails;
    private double totalDeliveryCost;
    private double rewardPoints;
    private double amountReceived;
    private LocalDate deliveryCreationDate;
    public NewDeliveryRegisteredEvent(String subscriberId, String subscriptionId, String deliveryId, LocalDate deliveryDate, int sequence, DeliveryDetails deliveyDetails, double totalDeliveryCost, double rewardPoints, double amountReceived,LocalDate deliveryCreationDate) {
        this.subscriberId=subscriberId;
        this.deliveryId=deliveryId;
        this.subscriptionId=subscriptionId;
        this.deliveryDate=deliveryDate;
        this.sequence=sequence;
        this.deliveryDetails=deliveyDetails;
        this.totalDeliveryCost=totalDeliveryCost;
        this.rewardPoints=rewardPoints;
        this.amountReceived=amountReceived;
        this.deliveryCreationDate=deliveryCreationDate;
    }


    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public double getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public int getSequence() {
        return sequence;
    }

    public double getAmountReceived() {
        return amountReceived;
    }

    public LocalDate getDeliveryCreationDate() {
        return deliveryCreationDate;
    }
}
