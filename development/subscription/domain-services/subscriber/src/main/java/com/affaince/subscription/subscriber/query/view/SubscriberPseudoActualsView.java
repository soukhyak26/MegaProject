package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/10/2017.
 */
@Document(collection="SubscriberPseudoActualsView")
public class SubscriberPseudoActualsView {
    @Id
    private LocalDate registrationDate;
    private long newSubscribers;
    private long churnedSubscribers;
    private long totalSubscribers;
    private ForecastContentStatus forecastContentStatus;
    private LocalDate forecastDate;

    public SubscriberPseudoActualsView(LocalDate registrationDate,LocalDate forecastDate){
        this.registrationDate = registrationDate;
        this.forecastDate=forecastDate;
    }

    public SubscriberPseudoActualsView(LocalDate registrationDate, long newSubscribers, long churnedSubscribers, long totalSubscribers, LocalDate forecastDate) {
        this.registrationDate = registrationDate;
        this.newSubscribers = newSubscribers;
        this.churnedSubscribers = churnedSubscribers;
        this.totalSubscribers = totalSubscribers;
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
        this.forecastDate=forecastDate;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
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


    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

}
