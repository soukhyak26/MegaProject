package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection="DeliveryForecastTrendView")
public class DeliveryForecastTrendView {
    @Id
    private DeliveryForecastVersionId deliveryForecastVersionId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private double referenceTotalDeliveriesCount;
    private double changeInTotalDeliveriesCount;

    public DeliveryForecastTrendView(){}
    public DeliveryForecastTrendView(LocalDate startDate, LocalDate endDate,LocalDate forecastDate,double weightRangeMin,double weightRangeMax, double referenceTotalDeliveriesCount, double changeInTotalDeliveriesCount) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate,forecastDate,weightRangeMin,weightRangeMax);
        this.endDate = endDate;
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
        this.referenceTotalDeliveriesCount=referenceTotalDeliveriesCount;
    }

    public LocalDate getStartDate() {
        return this.deliveryForecastVersionId.getDeliveryDate();
    }


    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getChangeInTotalDeliveriesCount() {
        return changeInTotalDeliveriesCount;
    }

    public void setChangeInTotalDeliveriesCount(double changeInTotalDeliveriesCount) {
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
    }

    public DeliveryForecastVersionId getDeliveryForecastVersionId() {
        return deliveryForecastVersionId;
    }

    public double getReferenceTotalDeliveriesCount() {
        return referenceTotalDeliveriesCount;
    }

    public void setReferenceTotalDeliveriesCount(double referenceTotalDeliveriesCount) {
        this.referenceTotalDeliveriesCount = referenceTotalDeliveriesCount;
    }
}
