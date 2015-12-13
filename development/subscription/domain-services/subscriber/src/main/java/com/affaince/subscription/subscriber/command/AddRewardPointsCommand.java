package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class AddRewardPointsCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private int rewardPoints;

    public AddRewardPointsCommand(String subscriberId, int rewardPoints) {
        this.subscriberId = subscriberId;
        this.rewardPoints = rewardPoints;
    }

    public AddRewardPointsCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
