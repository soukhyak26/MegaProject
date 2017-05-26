package com.affaince.subscription.payments.vo;

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

    public DeliveryDetails(String deliveryId, String subscriptionId) {
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
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
}
