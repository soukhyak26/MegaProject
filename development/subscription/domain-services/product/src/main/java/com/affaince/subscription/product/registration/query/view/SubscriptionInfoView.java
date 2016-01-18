package com.affaince.subscription.product.registration.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rsavaliya on 17/1/16.
 */
@Document(collection = "SubscriptionInfoView")
public class SubscriptionInfoView {
    @Id
    public String deliveryId;
    private String subscriberId;
    private String subscriptionId;
    private List<DeliveryItemInfoView> deliveryItems;
    private LocalDate deliveryDate;
    private double deliveryWeightInGrms;


    public SubscriptionInfoView(String deliveryId, String subscriberId, String subscriptionId, List<DeliveryItemInfoView> deliveryItems, LocalDate deliveryDate, double deliveryWeightInGrms) {
        this.deliveryId = deliveryId;
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.deliveryWeightInGrms = deliveryWeightInGrms;
    }

    public SubscriptionInfoView() {
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public List<DeliveryItemInfoView> getDeliveryItems() {
        return deliveryItems;
    }

    public void setDeliveryItems(List<DeliveryItemInfoView> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getDeliveryWeightInGrms() {
        return deliveryWeightInGrms;
    }

    public void setDeliveryWeightInGrms(double deliveryWeightInGrms) {
        this.deliveryWeightInGrms = deliveryWeightInGrms;
    }
}
