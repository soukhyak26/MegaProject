package com.verifier.domains.business.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 11/18/2017.
 */
@Document(collection="DeliveryForecastView")
public class DeliveryForecastView {
    @Id
    private DeliveryForecastVersionId deliveryForecastVersionId;
    private long deliveryCount;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryForecastView(){}
    public DeliveryForecastView(LocalDate startDate, LocalDate endDate,LocalDate forecastDate, double minWeight,double maxWeight) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate,forecastDate,minWeight,maxWeight);
        this.endDate = endDate;
        this.forecastContentStatus= ForecastContentStatus.ACTIVE;
    }

    public DeliveryForecastView( LocalDate startDate, LocalDate endDate,LocalDate forecastDate,long deliveryCount, double weightRangeMin, double weightRangeMax,ForecastContentStatus forecastContentStatus) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate,forecastDate,weightRangeMin,weightRangeMax);
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

}
