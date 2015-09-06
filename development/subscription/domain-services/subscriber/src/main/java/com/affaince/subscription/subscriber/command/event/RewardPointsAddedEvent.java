package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class RewardPointsAddedEvent {
    private String subscriberId;
    private int rewardPoints;

    public RewardPointsAddedEvent(String subscriberId, int rewardPoints) {
        this.subscriberId = subscriberId;
        this.rewardPoints = rewardPoints;
    }

    public RewardPointsAddedEvent() {
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
