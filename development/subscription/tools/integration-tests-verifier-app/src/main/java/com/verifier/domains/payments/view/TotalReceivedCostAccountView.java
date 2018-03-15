package com.verifier.domains.payments.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection = "TotalReceivedCostAccountView")
public class TotalReceivedCostAccountView {
    @Id
    private String subscriptionId;
    private double totalReceivedCost;

    public TotalReceivedCostAccountView(String subscriptionId, double totalReceivedCost) {
        this.subscriptionId = subscriptionId;
        this.totalReceivedCost = totalReceivedCost;
    }
    public void credit(double amount) {
        this.totalReceivedCost += amount;
    }

    public void debit(double amount) {
        this.totalReceivedCost  -= amount;
    }
    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTotalReceivedCost() {
        return totalReceivedCost;
    }
}
