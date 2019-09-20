package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection = "DeliveryPseudoActualsView")
public class DeliveryPseudoActualsView {
    @Id
    private DeliveryForecastVersionId deliveryForecastVersionId;
    private long deliveryCount;
    private ForecastContentStatus forecastContentStatus;

    public DeliveryPseudoActualsView(){}
    public DeliveryPseudoActualsView(LocalDate startDate,LocalDate forecastDate, double minWeight,double maxWeight) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate,forecastDate,minWeight,maxWeight);
        this.forecastContentStatus= ForecastContentStatus.ACTIVE;
    }

    public DeliveryPseudoActualsView(LocalDate startDate, LocalDate forecastDate,long deliveryCount, double weightRangeMin, double weightRangeMax,  ForecastContentStatus forecastContentStatus) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(startDate,forecastDate,weightRangeMin,weightRangeMax);
        this.deliveryCount = deliveryCount;
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

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }
}
