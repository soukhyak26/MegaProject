package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/22/2017.
 */
@Document(collection="SubscriptionForecastTrendView")
public class SubscriptionForecastTrendView {
    @Id
    private SubscriptionVersionId subscriptionVersionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private long changeInNewSubscriptionCount;
    private Long changeInChurnedSubscriptionCount;
    private long changeInTotalSubscriptionCount;


    public SubscriptionForecastTrendView(LocalDate startDate, LocalDate endDate,LocalDate forecastDate, double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(forecastDate,startDate,valueRangeMin,valueRangeMax);
        this.endDate=endDate;
    }

    public SubscriptionVersionId getSubscriptionVersionId() {
        return subscriptionVersionId;
    }

    public void setChangeInNewSubscriptionCount(long changeInNewSubscriptionCount) {
        this.changeInNewSubscriptionCount = changeInNewSubscriptionCount;
    }

    public void setChangeInChurnedSubscriptionCount(Long changeInChurnedSubscriptionCount) {
        this.changeInChurnedSubscriptionCount = changeInChurnedSubscriptionCount;
    }

    public long getChangeInNewSubscriptionCount() {
        return changeInNewSubscriptionCount;
    }

    public Long getChangeInChurnedSubscriptionCount() {
        return changeInChurnedSubscriptionCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getChangeInTotalSubscriptionCount() {
        return changeInTotalSubscriptionCount;
    }

    public void setChangeInTotalSubscriptionCount(long changeInTotalSubscriptionCount) {
        this.changeInTotalSubscriptionCount = changeInTotalSubscriptionCount;
    }
}
