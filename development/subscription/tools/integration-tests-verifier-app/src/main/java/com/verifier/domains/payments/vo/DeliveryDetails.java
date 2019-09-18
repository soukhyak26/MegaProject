package com.verifier.domains.payments.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 5/25/2017.
 */
public class DeliveryDetails {
    private String deliveryId;
    private String subscriptionId;
    private List<DeliverableProductDetail> deliverableProductDetails;
    private int deliverySequence;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;
    private double totalDeliveryCost;

    public DeliveryDetails(String deliveryId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<DeliverableProductDetail> getDeliverableProductDetails() {
        return deliverableProductDetails;
    }

    public void setDeliverableProductDetails(List<DeliverableProductDetail> deliverableProductDetails) {
        this.deliverableProductDetails = deliverableProductDetails;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public boolean isProductInDelivery(String productId){
        return deliverableProductDetails.stream().anyMatch(delivery->delivery.getDeliveryItemId().equals(productId));
    }

    public double getTotalDeliveryCost() {
        return totalDeliveryCost;
    }

    public void setTotalDeliveryCost(double totalDeliveryCost) {
        this.totalDeliveryCost = totalDeliveryCost;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public void setDeliverySequence(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }
}
