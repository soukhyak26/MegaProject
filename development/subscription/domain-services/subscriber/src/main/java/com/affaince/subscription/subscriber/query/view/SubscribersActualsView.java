package com.affaince.subscription.subscriber.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/1/2017.
 */
@Document(collection="SubscribersActualsView")
public class SubscribersActualsView {
    @Id
    private LocalDate registrationDate;
    private long newSubscribers;
    private long churnedSubscribers;
    private long totalSubscribers;

    public SubscribersActualsView(LocalDate registrationDate, long newSubscribers, long churnedSubscribers, long totalSubscribers) {
        this.registrationDate = registrationDate;
        this.newSubscribers = newSubscribers;
        this.churnedSubscribers = churnedSubscribers;
        this.totalSubscribers = totalSubscribers;
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

}
