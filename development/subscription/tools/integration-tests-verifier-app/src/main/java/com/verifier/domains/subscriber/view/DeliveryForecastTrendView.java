package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
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
    private LocalDate endDate;
    private double referenceTotalDeliveriesCount;
    private double changeInTotalDeliveriesCount;

    public DeliveryForecastTrendView() {
    }

    public DeliveryForecastTrendView(LocalDate trendSettingDate, double weightRangeMin, double weightRangeMax, LocalDate startDate, LocalDate endDate, double referenceTotalDeliveriesCount, double changeInTotalDeliveriesCount) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(trendSettingDate,startDate,weightRangeMin,weightRangeMax);
        this.endDate = endDate;
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
        this.referenceTotalDeliveriesCount=referenceTotalDeliveriesCount;
    }

    public LocalDate getTrendSettingDate() {
        return this.deliveryForecastVersionId.getForecastDate();
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
