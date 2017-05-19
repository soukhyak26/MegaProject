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

    public TotalReceivableCostAccountView(String subscriptionId, double totalReceivableCost) {
        this.subscriptionId = subscriptionId;
        this.totalReceivableCost = totalReceivableCost;
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

}
