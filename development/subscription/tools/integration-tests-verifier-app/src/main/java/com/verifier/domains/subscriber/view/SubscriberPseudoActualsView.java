package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/10/2017.
 */
@Document(collection="SubscriberPseudoActualsView")
public class SubscriberPseudoActualsView {
    @Id
    private SubscriberVersionId subscriberVersionId;
    private long newSubscribers;
    private long churnedSubscribers;
    private long totalSubscribers;
    private ForecastContentStatus forecastContentStatus;

    public SubscriberPseudoActualsView() {
    }

    public SubscriberPseudoActualsView(LocalDate startDate, LocalDate forecastDate){
        this.subscriberVersionId =new SubscriberVersionId(forecastDate,startDate);
    }

    public SubscriberPseudoActualsView(LocalDate startDate, long newSubscribers, long churnedSubscribers, long totalSubscribers, LocalDate forecastDate) {
        this.subscriberVersionId =new SubscriberVersionId(forecastDate,startDate);
        this.newSubscribers = newSubscribers;
        this.churnedSubscribers = churnedSubscribers;
        this.totalSubscribers = totalSubscribers;
        this.forecastContentStatus= ForecastContentStatus.ACTIVE;
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

    public SubscriberVersionId getSubscriberVersionId() {
        return subscriberVersionId;
    }
}
