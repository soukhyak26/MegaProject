package com.affaince.subscription.payments.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/17/2017.
 */
public class SubscriptionDetailsRegisteredEvent {
    private String subscriptionId;
    private double totalTentativeSubscriptionAmount;
    private double totalRewardPoints;
    private LocalDate registrationDate;
    public SubscriptionDetailsRegisteredEvent(String subscriptionId, double totalTentativeSubscriptionAmount, double totalRewardPoints,LocalDate registrationDate) {
        this.subscriptionId=subscriptionId;
        this.totalTentativeSubscriptionAmount=totalTentativeSubscriptionAmount;
        this.totalRewardPoints=totalRewardPoints;
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
}