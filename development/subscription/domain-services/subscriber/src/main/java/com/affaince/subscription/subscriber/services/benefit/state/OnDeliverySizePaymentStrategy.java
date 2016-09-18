package com.affaince.subscription.subscriber.services.benefit.state;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public class OnDeliverySizePaymentStrategy implements PaymentStrategy {
    @Override
    public Map<String, Double> distributeRewardPoints(Map<String, Double> deliveryValues, double rewardPoints) {
        final Map<String, Double> deliveryWiseRewardsDistributions = new HashMap<>(deliveryValues.size());
        double totalDeliveriesValue = deliveryValues.values().stream().mapToDouble(i -> i.doubleValue()).sum();
        for (String deliveryId : deliveryValues.keySet()) {
            deliveryWiseRewardsDistributions.put(deliveryId,
                    (rewardPoints * deliveryValues.get(deliveryId)) / totalDeliveriesValue);
        }
        return deliveryWiseRewardsDistributions;
    }
}
