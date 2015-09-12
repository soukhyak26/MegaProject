package com.affaince.subscription.subscriber.web.request;

import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class AddRewardPointsRequest {

    @NotNull
    private int rewardPoints;

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
