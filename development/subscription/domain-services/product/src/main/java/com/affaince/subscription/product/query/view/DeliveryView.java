package com.affaince.subscription.product.query.view;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ReasonCode;
import com.affaince.subscription.common.vo.DeliveryId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Document(collection = "DeliveryView")
public class DeliveryView {
    @Id
    private DeliveryId deliveryId;
    private List<DeliveryItem> deliveryItems;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate deliveryDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate dispatchDate;
    private DeliveryStatus status;
    private ReasonCode reasonCode;

    public DeliveryView(DeliveryId deliveryId, List<DeliveryItem> deliveryItems, LocalDate deliveryDate, LocalDate dispatchDate, DeliveryStatus status, ReasonCode reasonCode) {
        this.deliveryId = deliveryId;
        this.deliveryItems = deliveryItems;
        this.deliveryDate = deliveryDate;
        this.dispatchDate = dispatchDate;
        this.status = status;
        this.reasonCode = reasonCode;
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
}
