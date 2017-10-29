package com.affaince.subscription.forecast.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.forecast.vo.DeliveryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryPseudoActualsView")
public class DeliveryPseudoActualsView {
    @Id
    private DeliveryVersionId deliveryVersionId;
    private long deliveryCount;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryPseudoActualsView(LocalDate forecastDate, LocalDate startDate,double minWeight,double maxWeight) {
        this.deliveryVersionId = new DeliveryVersionId(forecastDate,startDate,minWeight,maxWeight);
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
    }

    public DeliveryPseudoActualsView(LocalDate startDate, LocalDate forecastDate,long deliveryCount, double weightRangeMin, double weightRangeMax,  ForecastContentStatus forecastContentStatus) {
        this.deliveryVersionId = new DeliveryVersionId(forecastDate,startDate,weightRangeMin,weightRangeMax);
        this.deliveryCount = deliveryCount;
        this.forecastContentStatus = forecastContentStatus;
    }

    public DeliveryVersionId getDeliveryVersionId() {
        return deliveryVersionId;
    }

    public long getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(long deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
