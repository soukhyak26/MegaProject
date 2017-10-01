package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/22/2017.
 */
public class SubscriberForecastTrendView {
    private SubscriberVersionId subscriberVersionId;
    private LocalDate endDate;
    private double changeInNewSubscriberCount;
    private double changeInChurnedSubscriberCount;
    private double changeInTotalSubscriberCount;

    public SubscriberForecastTrendView(LocalDate forecastDate, LocalDate startDate, LocalDate endDate) {
        this.subscriberVersionId =new SubscriberVersionId(forecastDate, startDate);
        this.endDate = endDate;
    }


    public SubscriberVersionId getSubscriberVersionId() {
        return subscriberVersionId;
    }

    public void setSubscriberVersionId(SubscriberVersionId subscriberVersionId) {
        this.subscriberVersionId = subscriberVersionId;
    }

    public double getChangeInNewSubscriberCount() {
        return changeInNewSubscriberCount;
    }

    public void setChangeInNewSubscriberCount(double changeInNewSubscriberCount) {
        this.changeInNewSubscriberCount = changeInNewSubscriberCount;
    }

    public double getChangeInChurnedSubscriberCount() {
        return changeInChurnedSubscriberCount;
    }

    public void setChangeInChurnedSubscriberCount(double changeInChurnedSubscriberCount) {
        this.changeInChurnedSubscriberCount = changeInChurnedSubscriberCount;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getChangeInTotalSubscriberCount() {
        return changeInTotalSubscriberCount;
    }

    public void setChangeInTotalSubscriberCount(double changeInTotalSubscriberCount) {
        this.changeInTotalSubscriberCount = changeInTotalSubscriberCount;
    }

}
