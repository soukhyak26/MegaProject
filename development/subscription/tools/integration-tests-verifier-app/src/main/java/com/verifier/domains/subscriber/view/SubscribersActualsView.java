package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 9/1/2017.
 */
@Document(collection="SubscribersActualsView")
public class SubscribersActualsView {
    @Id
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate registrationDate;
    private long newSubscribers;
    private long churnedSubscribers;
    private long totalSubscribers;

    public SubscribersActualsView() {
    }

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
