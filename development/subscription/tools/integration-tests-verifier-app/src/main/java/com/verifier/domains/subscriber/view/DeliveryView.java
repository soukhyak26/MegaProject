package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.common.vo.CompositeDeliveryId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "DeliveryView")
public class DeliveryView {
    @Id
    private CompositeDeliveryId compositeDeliveryId;
    private List<DeliveryItem> deliveryItems;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliveryDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate acknowledgementDate;
    private DeliveryStatus deliveryStatus;
    private ReasonCode reasonCode;
    private double rewardPoints;

    public DeliveryView(CompositeDeliveryId compositeDeliveryId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, DeliveryStatus deliveryStatus, double rewardPoints) {
        this.compositeDeliveryId = compositeDeliveryId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.rewardPoints = rewardPoints;
    }

    public DeliveryView() {
    }

    public CompositeDeliveryId getCompositeDeliveryId() {
        return compositeDeliveryId;
    }

    public void setCompositeDeliveryId(CompositeDeliveryId compositeDeliveryId) {
        this.compositeDeliveryId = compositeDeliveryId;
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

    public LocalDate getAcknowledgementDate() {
        return acknowledgementDate;
    }

    public void setAcknowledgementDate(LocalDate acknowledgementDate) {
        this.acknowledgementDate = acknowledgementDate;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
