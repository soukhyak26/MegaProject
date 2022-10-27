package com.affaince.distribution.db;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

public class DeliveryForecastView {
    @Id
    private DeliveryForecastVersionId deliveryForecastVersionId;
    private long deliveryCount;
    private LocalDate endDate;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryForecastView() {
    }

    public DeliveryForecastView(LocalDate startDate, LocalDate endDate, LocalDate forecastDate, double minWeight, double maxWeight) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate, forecastDate, minWeight, maxWeight);
        this.endDate = endDate;
        this.forecastContentStatus = ForecastContentStatus.ACTIVE;
    }

    public DeliveryForecastView(LocalDate startDate, LocalDate endDate, LocalDate forecastDate, double weightRangeMin, double weightRangeMax, long deliveryCount, ForecastContentStatus forecastContentStatus) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate, forecastDate, weightRangeMin, weightRangeMax);
        this.deliveryCount = deliveryCount;
        this.endDate = endDate;
        this.forecastContentStatus = forecastContentStatus;
    }

    public DeliveryForecastVersionId getDeliveryForecastVersionId() {
        return deliveryForecastVersionId;
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

    @Override
    public String toString() {
        return "DeliveryForecastView{" +
                "deliveryForecastVersionId=" + deliveryForecastVersionId +
                ", deliveryCount=" + deliveryCount +
                ", endDate=" + endDate +
                ", forecastContentStatus=" + forecastContentStatus +
                '}';
    }
}
