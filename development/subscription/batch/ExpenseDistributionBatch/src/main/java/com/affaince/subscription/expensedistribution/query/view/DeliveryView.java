package com.affaince.subscription.expensedistribution.query.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.common.vo.DeliveryId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public class DeliveryView {
    private DeliveryId deliveryId;
    private List<DeliveryItem> deliveryItems;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliveryDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private ReasonCode reasonCode;
    private double rewardPoints;

    public DeliveryView(DeliveryId deliveryId, String subscriberId, String subscriptionId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, DeliveryStatus status, double rewardPoints) {
        this.deliveryId = deliveryId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.status = status;
        this.rewardPoints = rewardPoints;
    }

    public DeliveryView() {
    }

    public DeliveryId getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(DeliveryId deliveryId) {
        this.deliveryId = deliveryId;
    }

    public List<DeliveryItem> getDeliveryItems() {
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

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
