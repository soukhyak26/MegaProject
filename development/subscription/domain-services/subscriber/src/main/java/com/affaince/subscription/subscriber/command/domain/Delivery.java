package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class Delivery {
    private String deliveryId;
    private List<DeliveryItem> deliveryItems;
    private LocalDate deliveryDate;
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private double deliveryCharges;
    private double totalWeight;
    private double totalDeliveryPrice;
    private double rewardPoints;
    private ReasonCode reasonCode;

    public Delivery(String deliveryId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status) {
        this.deliveryId = deliveryId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
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

    public void calculateTotalWeightInGrams() {
        totalWeight = 0;
        for (DeliveryItem deliveryItem : this.deliveryItems) {
            totalWeight = totalWeight + deliveryItem.getWeightInGrms();
        }
    }

    public void calculateItemLevelDeliveryCharges(DeliveryChargesRule deliveryChargesRule) {
        List<RangeRule> rangeRules = deliveryChargesRule.getDeliveryChargesRules();
        for (RangeRule rangeRule:rangeRules) {
            if (totalWeight > rangeRule.getRuleMinimum() && totalWeight < rangeRule.getRuleMaximum()) {
                deliveryCharges = rangeRule.getApplicableValue();
                break;
            }
        }
        for (DeliveryItem item: deliveryItems) {
            totalDeliveryPrice = totalDeliveryPrice + item.getOfferedPricePerUnit();
            item.setDeliveryCharges((item.getOfferedPricePerUnit() * deliveryCharges) / totalDeliveryPrice);
        }
    }
}
