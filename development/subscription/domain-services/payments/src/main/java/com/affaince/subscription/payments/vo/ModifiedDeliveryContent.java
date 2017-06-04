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
    private String deliveryId;
    private List<DeliveredProductDetail> items;
    private double correctedTotalPayment;
    private double correctedRemainingDuePayment;

    public ModifiedDeliveryContent(String subscriptionId,String deliveryId) {
        this.subscriptionId = subscriptionId;
        this.deliveryId=deliveryId;
        this.items=new ArrayList<>();
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
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
