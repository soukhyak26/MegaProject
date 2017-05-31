package com.affaince.subscription.payments.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 5/28/2017.
 */
public class ModifiedDeliveryContent {
    private String subscriptionId;
    private List<DeliveredProductDetail> items;
    private double correctedTotalPayment;
    private double correctedRemainingDuePayment;

    public ModifiedDeliveryContent(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        this.items=new ArrayList<>();
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<DeliveredProductDetail> getItems() {
        return items;
    }
    public void addtoItems(DeliveredProductDetail deliverableItem){
        this.items.add(deliverableItem);
    }

    public double getCorrectedTotalPayment() {
        return correctedTotalPayment;
    }

    public void setCorrectedTotalPayment(double correctedTotalPayment) {
        this.correctedTotalPayment = correctedTotalPayment;
    }

    public double getCorrectedRemainingDuePayment() {
        return correctedRemainingDuePayment;
    }

    public void setCorrectedRemainingDuePayment(double correctedRemainingDuePayment) {
        this.correctedRemainingDuePayment = correctedRemainingDuePayment;
    }
}
