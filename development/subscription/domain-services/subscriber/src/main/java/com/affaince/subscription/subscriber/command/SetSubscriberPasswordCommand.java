package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-12-2015.
 */
public class SetSubscriberPasswordCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String password;

    public SetSubscriberPasswordCommand(String subscriberId, String password) {
        this.subscriberId = subscriberId;
        this.password = password;
    }

    public SetSubscriberPasswordCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getPassword() {
        return password;
    }
}
