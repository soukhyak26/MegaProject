package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/10/2017.
 */
@Document(collection="SubscriptionPseudoActualsView")
public class SubscriptionPseudoActualsView {
    @Id
    private SubscriptionVersionId subscriptionVersionId;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalSubscriptions;
    private ForecastContentStatus forecastContentStatus;

    public SubscriptionPseudoActualsView() {
    }

    public SubscriptionPseudoActualsView(LocalDate startDate, LocalDate forecastDate, double valueRangeMin, double valueRangeMax){
        this.subscriptionVersionId = new SubscriptionVersionId(forecastDate,startDate,valueRangeMin,valueRangeMax);
    }
    public SubscriptionPseudoActualsView(LocalDate startDate, long newSubscriptions, long churnedSubscriptions, long totalSubscriptions,LocalDate forecastDate,double valueRangeMin, double valueRangeMax) {
        this.subscriptionVersionId = new SubscriptionVersionId(forecastDate,startDate,valueRangeMin,valueRangeMax);
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalSubscriptions = totalSubscriptions;
        this.forecastContentStatus= ForecastContentStatus.ACTIVE;
    }

    public void addToNewSubscriptionCount(long subscriptionCount){
        this.newSubscriptions+=subscriptionCount;
    }
    public void addToChurnedSubscriptionCount(long subscriptionCount){
        this.churnedSubscriptions +=subscriptionCount;
    }
    public void addToTotalSubscriptionCount(long subscriptionCount){
        this.totalSubscriptions+=subscriptionCount;
    }


    public long getNewSubscriptions() {
        return newSubscriptions;
    }

    public long getChurnedSubscriptions() {
        return churnedSubscriptions;
    }

    public long getTotalSubscriptions() {
        return totalSubscriptions;
    }

    public void setNewSubscriptions(long newSubscriptions) {
        this.newSubscriptions = newSubscriptions;
    }

    public void setChurnedSubscriptions(long churnedSubscriptions) {
        this.churnedSubscriptions = churnedSubscriptions;
    }

    public void setTotalSubscriptions(long totalSubscriptions) {
        this.totalSubscriptions = totalSubscriptions;
    }

    public ForecastContentStatus getForecastContentStatus() {
        return forecastContentStatus;
    }

    public void setForecastContentStatus(ForecastContentStatus forecastContentStatus) {
        this.forecastContentStatus = forecastContentStatus;
    }

    public SubscriptionVersionId getSubscriptionVersionId() {
        return subscriptionVersionId;
    }

}
