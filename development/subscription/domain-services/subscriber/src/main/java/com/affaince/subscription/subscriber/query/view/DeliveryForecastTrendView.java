package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
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
    private long changeInTotalDeliveriesCount;

    public DeliveryForecastTrendView(LocalDate trendSettingDate,double weightRangeMin,double weightRangeMax, LocalDate startDate, LocalDate endDate, long changeInTotalDeliveriesCount) {
        this.deliveryForecastVersionId = new DeliveryForecastVersionId(trendSettingDate,startDate,weightRangeMin,weightRangeMax);
        this.endDate = endDate;
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
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

    public long getChangeInTotalDeliveriesCount() {
        return changeInTotalDeliveriesCount;
    }

    public void setChangeInTotalDeliveriesCount(long changeInTotalDeliveriesCount) {
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
    }

    public DeliveryForecastVersionId getDeliveryForecastVersionId() {
        return deliveryForecastVersionId;
    }
}
