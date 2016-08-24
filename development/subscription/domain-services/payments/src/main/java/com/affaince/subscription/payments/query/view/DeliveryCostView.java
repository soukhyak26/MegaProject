package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.command.accounting.DeliveryCostAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 21/8/16.
 */
@Document(collection = "DeliveryCostView")
public class DeliveryCostView {
    @Id
    private String deliveryId;
    private String subscriptionId;
    private double deliveryCost;
    private DeliveryCostAccount deliveryCostAccount;

    public DeliveryCostView() {
        this.deliveryCostAccount = new DeliveryCostAccount(0);
    }

    public DeliveryCostView(String deliveryId, String subscriptionId, double deliveryCost) {
        this();
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public DeliveryCostAccount getDeliveryCostAccount() {
        return deliveryCostAccount;
    }
}
