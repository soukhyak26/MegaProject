package com.affaince.subscription.delivery.query.view;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
@Document(collection = "DeliveryView")
public class DeliveryView {
    @Id
    private DeliveryId deliveryId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate deliveryDate;
    private DeliveryStatus status;

    public DeliveryView() {
    }

    public DeliveryId getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(DeliveryId deliveryId) {
        this.deliveryId = deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
