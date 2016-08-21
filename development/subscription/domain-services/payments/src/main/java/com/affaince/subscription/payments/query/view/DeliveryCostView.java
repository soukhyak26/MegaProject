package com.affaince.subscription.payments.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by anayonkar on 21/8/16.
 */
@Document(collection = "DeliveryCostView")
public class DeliveryCostView {
    @Id
    private String deliveryId;
    private double deliveryCost;

    public DeliveryCostView() {
    }

    public DeliveryCostView(String deliveryId, double deliveryCost) {
        this.deliveryId = deliveryId;
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }
}
