package com.affaince.subscription.payments.vo;

import com.affaince.subscription.common.type.DeliveryStatus;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandar on 5/25/2017.
 */
public class DeliveryDetails {
    private String deliveryId;
    private String subscriptionId;
    private List<DeliveredProductDetail> deliveredProductDetails;
    private LocalDate deliveryDate;
    private DeliveryStatus deliveryStatus;

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

    public List<DeliveredProductDetail> getDeliveredProductDetails() {
        return deliveredProductDetails;
    }

    public void setDeliveredProductDetails(List<DeliveredProductDetail> deliveredProductDetails) {
        this.deliveredProductDetails = deliveredProductDetails;
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
}
