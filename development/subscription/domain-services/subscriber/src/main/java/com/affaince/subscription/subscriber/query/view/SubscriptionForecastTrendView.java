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
    private double changeInNewSubscriptionCount;
    private double changeInChurnedSubscriptionCount;
    private double changeInTotalSubscriptionCount;


    public SubscriptionForecastTrendView(LocalDate startDate, LocalDate endDate,LocalDate forecastDate, double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(forecastDate,startDate,valueRangeMin,valueRangeMax);
        this.endDate=endDate;
    }

    public SubscriptionVersionId getSubscriptionVersionId() {
        return subscriptionVersionId;
    }

    public void setChangeInNewSubscriptionCount(double changeInNewSubscriptionCount) {
        this.changeInNewSubscriptionCount = changeInNewSubscriptionCount;
    }

    public void setChangeInChurnedSubscriptionCount(double changeInChurnedSubscriptionCount) {
        this.changeInChurnedSubscriptionCount = changeInChurnedSubscriptionCount;
    }

    public double getChangeInNewSubscriptionCount() {
        return changeInNewSubscriptionCount;
    }

    public double getChangeInChurnedSubscriptionCount() {
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

    public double getChangeInTotalSubscriptionCount() {
        return changeInTotalSubscriptionCount;
    }

    public void setChangeInTotalSubscriptionCount(double changeInTotalSubscriptionCount) {
        this.changeInTotalSubscriptionCount = changeInTotalSubscriptionCount;
    }
}
