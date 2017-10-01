package com.affaince.subscription.subscriber.command.event;

/**
 * Created by mandar on 10/1/2017.
 */
public class SubscriptionAnalyserCreatedEvent {
    private Integer subscriptionAnalyserId;
    public SubscriptionAnalyserCreatedEvent(Integer subscriptionAnalyserId) {
        this.subscriptionAnalyserId=subscriptionAnalyserId;
    }

    public Integer getSubscriptionAnalyserId() {
        return subscriptionAnalyserId;
    }
}
