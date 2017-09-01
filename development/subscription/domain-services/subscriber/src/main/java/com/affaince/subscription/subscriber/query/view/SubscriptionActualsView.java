package com.affaince.subscription.subscriber.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 8/30/2017.
 */
@Document(collection="SubscriptionActualsView")
public class SubscriptionActualsView {
    @Id
    private LocalDate registrationDate;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalSubscriptions;

    public SubscriptionActualsView(LocalDate registrationDate, long newSubscriptions, long churnedSubscriptions, long totalSubscriptions) {
        this.registrationDate = registrationDate;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalSubscriptions = totalSubscriptions;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
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
}
