package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 5/17/2017.
 */
public class SubscriptionDetailsRegisteredEvent {
    private String subscriptionId;
    private double totalTentativeSubscriptionAmount;
    private double totalRewardPoints;
    private List<DeliveryDetails> deliveries;
    private LocalDate registrationDate;
    public SubscriptionDetailsRegisteredEvent(String subscriptionId, double totalTentativeSubscriptionAmount, double totalRewardPoints,List<DeliveryDetails> deliveries,LocalDate registrationDate) {
        this.subscriptionId=subscriptionId;
        this.totalTentativeSubscriptionAmount=totalTentativeSubscriptionAmount;
        this.totalRewardPoints=totalRewardPoints;
        this.deliveries=deliveries;
        this.registrationDate=registrationDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTotalTentativeSubscriptionAmount() {
        return totalTentativeSubscriptionAmount;
    }

    public double getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public List<DeliveryDetails> getDeliveries() {
        return deliveries;
    }
}
