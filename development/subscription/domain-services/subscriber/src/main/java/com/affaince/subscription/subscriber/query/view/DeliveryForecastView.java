package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryForecastView")
public class DeliveryForecastView {
    @Id
    private DeliveryVersionId deliveryVersionId;
    private long deliveryCount;
    private LocalDate endDate;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryForecastView(LocalDate forecastDate, LocalDate startDate, LocalDate endDate,double minWeight,double maxWeight) {
        this.deliveryVersionId = new DeliveryVersionId(forecastDate,startDate,minWeight,maxWeight);
        this.endDate = endDate;
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
    }

    public DeliveryForecastView(LocalDate forecastDate, double weightRangeMin, double weightRangeMax, long deliveryCount, LocalDate startDate, LocalDate endDate, ForecastContentStatus forecastContentStatus) {
        this.deliveryVersionId = new DeliveryVersionId(forecastDate,startDate,weightRangeMin,weightRangeMax);
        this.deliveryCount = deliveryCount;
        this.endDate = endDate;
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


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
