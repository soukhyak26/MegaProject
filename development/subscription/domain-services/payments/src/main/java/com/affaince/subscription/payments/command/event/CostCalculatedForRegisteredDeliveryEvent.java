package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.joda.time.LocalDate;

import java.util.Map;

/**
 * Created by mandar on 5/17/2017.
 */
public class CostCalculatedForRegisteredDeliveryEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private int sequence;
    private DeliveryDetails deliveryDetails;
    private double totalDeliveryCost;
    private double rewardPoints;
    private Map<Integer,Double> deliverySequencewiseMoneyDistribution;
    public CostCalculatedForRegisteredDeliveryEvent(String subscriberId,String subscriptionId, String deliveryId, LocalDate deliveryDate,int sequence, DeliveryDetails deliveyDetails, double totalDeliveryCost,double rewardPoints,Map<Integer,Double> deliverySequencewiseMoneyDistribution) {
        this.subscriberId=subscriberId;
        this.deliveryId=deliveryId;
        this.subscriptionId=subscriptionId;
        this.deliveryDate=deliveryDate;
        this.sequence=sequence;
        this.deliveryDetails=deliveyDetails;
        this.totalDeliveryCost=totalDeliveryCost;
        this.rewardPoints=rewardPoints;
        this.deliverySequencewiseMoneyDistribution=deliverySequencewiseMoneyDistribution;
    }

    public Map<Integer, Double> getDeliverySequencewiseMoneyDistribution() {
        return deliverySequencewiseMoneyDistribution;
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
}
