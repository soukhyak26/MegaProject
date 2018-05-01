package com.verifier.domains.payments.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection = "TotalSubscriptionCostAccountView")
public class TotalSubscriptionCostAccountView {
    @Id
    private String subscriptionId;
    private double totalSubscriptionCost;

    public TotalSubscriptionCostAccountView() {
    }

    public TotalSubscriptionCostAccountView(String subscriptionId, double totalSubscriptionCost) {
        this.subscriptionId = subscriptionId;
        this.totalSubscriptionCost = totalSubscriptionCost;
    }

    public void credit(double amount) {
        this.totalSubscriptionCost += amount;
    }

    public void debit(double amount) {
        this.totalSubscriptionCost  -= amount;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTotalSubscriptionCost() {
        return totalSubscriptionCost;
    }
    public void setOrOverride(double amount){
        this.totalSubscriptionCost= amount;
    }

}
