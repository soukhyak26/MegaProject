package com.affaince.subscription.benefit.simulator.Delivery;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class Delivery {
    private String deliveryId;
    private int sequence;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private double deliveryCharges;
    private double totalWeight;
    private double totalDeliveryPrice;
    private double rewardPoints;
    private ReasonCode reasonCode;

    public Delivery(String deliveryId, int sequence, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status, double rewardPoints) {
        this.deliveryId = deliveryId;
        this.sequence=sequence;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
        this.rewardPoints = rewardPoints;
    }

    public Delivery() {
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<DeliveryItem> getDeliveryItems() {
        if (this.deliveryItems == null) {
            this.deliveryItems = new ArrayList<>();
        }
        return deliveryItems;
    }

    public void setDeliveryItems(List<DeliveryItem> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public double getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(double deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getTotalDeliveryPrice() {
        return totalDeliveryPrice;
    }

    public void setTotalDeliveryPrice(double totalDeliveryPrice) {
        this.totalDeliveryPrice = totalDeliveryPrice;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public int getSequence() {
        return sequence;
    }

    public void calculateTotalWeightInGrams() {
        totalWeight = 0;
        for (DeliveryItem deliveryItem : this.deliveryItems) {
            totalWeight = totalWeight + deliveryItem.getWeightInGrms();
        }
    }

    public void calculateTotalDeliveryPrice() {
        this.totalDeliveryPrice =
                this.deliveryItems.stream().mapToDouble(DeliveryItem::getOfferedPricePerUnit).sum();
    }
}
