package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.vo.DeliveryActualsVersionId;
import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryActualsView")
public class DeliveryActualsView {
    @Id
    private DeliveryActualsVersionId deliveryActualsVersionId;
    private long deliveryCount;

    public DeliveryActualsView(LocalDate deliveryDate, double minWeight, double maxWeight) {
        this.deliveryActualsVersionId = new DeliveryActualsVersionId(deliveryDate,minWeight,maxWeight);
    }

    public DeliveryActualsView(LocalDate deliveryDate,long deliveryCount, double weightRangeMin, double weightRangeMax) {
        this.deliveryActualsVersionId = new DeliveryActualsVersionId(deliveryDate,weightRangeMin,weightRangeMax);
        this.deliveryCount = deliveryCount;
    }

    public DeliveryActualsVersionId getDeliveryActualsVersionId() {
        return deliveryActualsVersionId;
    }

    public long getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(long deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

}
