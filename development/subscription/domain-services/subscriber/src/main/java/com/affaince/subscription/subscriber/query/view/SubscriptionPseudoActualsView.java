package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.ForecastContentStatus;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/10/2017.
 */
@Document(collection="SubscriptionPseudoActualsView")
public class SubscriptionPseudoActualsView {
    @Id
    private LocalDate registrationDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalSubscriptions;
    private ForecastContentStatus forecastContentStatus;
    private LocalDate forecastDate;

    public SubscriptionPseudoActualsView(LocalDate registrationDate, LocalDate forecastDate){
        this.registrationDate = registrationDate;
        this.forecastDate=forecastDate;
    }

    public SubscriptionPseudoActualsView(LocalDate registrationDate, long newSubscriptions, long churnedSubscriptions, long totalSubscriptions,LocalDate forecastDate) {
        this.registrationDate = registrationDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalSubscriptions = totalSubscriptions;
        this.forecastContentStatus=ForecastContentStatus.ACTIVE;
        this.forecastDate=forecastDate;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
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


    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
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

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

}
