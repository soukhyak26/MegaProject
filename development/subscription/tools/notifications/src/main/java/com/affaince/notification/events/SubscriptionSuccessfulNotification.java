package com.affaince.notification.events;

import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 16/1/16.
 */
@Component
public class SubscriptionSuccessfulNotification {
    private String subscriptionId;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }


    @Override
    public String toString() {
        return "SubscriptionSuccessfulNotification{" +
                "subscriptionId='" + subscriptionId + '\'' +
                '}';
    }
}
