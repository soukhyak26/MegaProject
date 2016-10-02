package com.affaince.subscription.subscriber.services.benefit.state;

import java.util.Map;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public interface PaymentStrategy {
    Map<String, Double> distributeRewardPoints(Map<String, Double> deliveryValues, double rewardPoints);
}