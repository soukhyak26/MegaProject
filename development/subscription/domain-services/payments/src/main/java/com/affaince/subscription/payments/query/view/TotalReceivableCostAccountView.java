package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.date.SysDate;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection = "TotalReceivableCostAccountView")
public class TotalReceivableCostAccountView {
    @Id
    private String subscriptionId;
    private double totalReceivableCost;
    private double rewardPoints;

    public TotalReceivableCostAccountView(String subscriptionId, double totalReceivableCost) {
        this.subscriptionId = subscriptionId;
        this.totalReceivableCost = totalReceivableCost;
    }

    public void addToRewardPoints(double rewardPoints){
        this.rewardPoints+=rewardPoints;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void credit(double amount) {
        this.totalReceivableCost += amount;
    }

    public void debit(double amount) {
        this.totalReceivableCost  -= amount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTotalReceivableCost() {
        return totalReceivableCost;
    }
    public void setOrOverride(double amount){
        this.totalReceivableCost= amount;
    }
}
