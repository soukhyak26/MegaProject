package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 26-12-2015.
 */
public class SubscriberPasswordSetEvent {
    private String subscriberId;
    private String password;

    public SubscriberPasswordSetEvent(String subscriberId, String password) {
        this.subscriberId = subscriberId;
        this.password = password;
    }

    public SubscriberPasswordSetEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
