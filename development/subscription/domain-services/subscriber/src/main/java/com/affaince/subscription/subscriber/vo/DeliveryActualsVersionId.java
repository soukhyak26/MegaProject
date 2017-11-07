package com.affaince.subscription.subscriber.vo;

import org.joda.time.LocalDate;
import scala.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class DeliveryActualsVersionId implements Serializable{
    private final LocalDate deliveryDate;
    private final double weightRangeMin;
    private final double weightRangeMax;

    public DeliveryActualsVersionId(LocalDate deliveryDate, double weightRangeMin, double weightRangeMax) {
        this.deliveryDate = deliveryDate;
        this.weightRangeMin = weightRangeMin;
        this.weightRangeMax = weightRangeMax;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public double getWeightRangeMin() {
        return weightRangeMin;
    }

    public double getWeightRangeMax() {
        return weightRangeMax;
    }
}
