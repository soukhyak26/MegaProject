package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/1/2017.
 */
@Document(collection="SubscribersForecastView")
public class SubscribersForecastView {
    @Id
    private SubscriberVersionId subscriberVersionId;
    private LocalDate endDate;
    private long newSubscribers;
    private long churnedSubscribers;
    private long totalSubscribers;
    private ForecastContentStatus forecastContentStatus;

    public SubscribersForecastView(LocalDate startDate,LocalDate endDate,LocalDate forecastDate){
        this.subscriberVersionId = new SubscriberVersionId(startDate,forecastDate);
        this.endDate=endDate;
    }

    public SubscribersForecastView(LocalDate startDate, LocalDate endDate,long newSubscribers, long churnedSubscribers, long totalSubscribers,LocalDate forecastDate) {
        this.subscriberVersionId = new SubscriberVersionId(startDate,forecastDate);
        this.endDate=endDate;
        this.newSubscribers = newSubscribers;
        this.churnedSubscribers = churnedSubscribers;
        this.totalSubscribers = totalSubscribers;
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
    }

    public void addToNewSubscriptionCount(long subscriptionCount){
        this.newSubscribers +=subscriptionCount;
    }
    public void addToChurnedSubscriptionCount(long subscriptionCount){
        this.churnedSubscribers +=subscriptionCount;
    }
    public void addToTotalSubscriptionCount(long subscriptionCount){
        this.totalSubscribers +=subscriptionCount;
    }


    public long getNewSubscribers() {
        return newSubscribers;
    }

    public long getChurnedSubscribers() {
        return churnedSubscribers;
    }

    public long getTotalSubscribers() {
        return totalSubscribers;
    }


    public void setNewSubscribers(long newSubscribers) {
        this.newSubscribers = newSubscribers;
    }

    public void setChurnedSubscribers(long churnedSubscribers) {
        this.churnedSubscribers = churnedSubscribers;
    }

    public void setTotalSubscribers(long totalSubscribers) {
        this.totalSubscribers = totalSubscribers;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SubscriberVersionId getSubscriberVersionId() {
        return subscriberVersionId;
    }
}
