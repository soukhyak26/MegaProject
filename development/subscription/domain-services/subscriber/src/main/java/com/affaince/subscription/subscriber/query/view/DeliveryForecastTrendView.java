package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/23/2017.
 */
@Document(collection="DeliveryForecastTrendView")
public class DeliveryForecastTrendView {
    @Id
    private DeliveryVersionId deliveryVersionId;
    private LocalDate endDate;
    private long changeInTotalDeliveriesCount;

    public DeliveryForecastTrendView(LocalDate trendSettingDate,double weightRangeMin,double weightRangeMax, LocalDate startDate, LocalDate endDate, long changeInTotalDeliveriesCount) {
        this.deliveryVersionId = new DeliveryVersionId(trendSettingDate,startDate,weightRangeMin,weightRangeMax);
        this.endDate = endDate;
        this.changeInTotalDeliveriesCount = changeInTotalDeliveriesCount;
    }

    public LocalDate getTrendSettingDate() {
        return this.deliveryVersionId.getForecastDate();
    }


    public LocalDate getStartDate() {
        return this.deliveryVersionId.getStartDate();
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
}
