package com.verifier.domains.subscriber.view;

import com.verifier.domains.subscriber.vo.SubscriptionActualsVersionId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 8/30/2017.
 */
@Document(collection="SubscriptionActualsView")
public class SubscriptionActualsView {
    @Id
    private SubscriptionActualsVersionId subscriptionActualsVersionId;
    private long newSubscriptions;
    private long churnedSubscriptions;
    private long totalSubscriptions;

    public SubscriptionActualsView() {
    }

    public SubscriptionActualsView(SubscriptionActualsVersionId subscriptionActualsVersionId, long newSubscriptions, long churnedSubscriptions, long totalSubscriptions) {
        this.subscriptionActualsVersionId = subscriptionActualsVersionId;
        this.newSubscriptions = newSubscriptions;
        this.churnedSubscriptions = churnedSubscriptions;
        this.totalSubscriptions = totalSubscriptions;
    }

    public SubscriptionActualsVersionId getSubscriptionActualsVersionId() {
        return subscriptionActualsVersionId;
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

    public void setSubscriptionActualsVersionId(SubscriptionActualsVersionId subscriptionActualsVersionId) {
        this.subscriptionActualsVersionId = subscriptionActualsVersionId;
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
}
