package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public class IncrementalPaymentStrategy implements PaymentStrategy {
    @Override
    public void distributeRewardPoints(BenefitExecutionContext context) {
        final Map<String, Double> deliveryValues = context.getRequest().getDeliveryAmounts();
        final double rewardPoints = context.getRewardPoints() - context.getRequest().getRewardPointAdjustment();
        final Map<String, Double> deliveryWiseRewardsDistributions = new HashMap<>(deliveryValues.size());
        double multiplier = findMultiplier(deliveryValues.size(), rewardPoints);
        int i = 0;
        boolean isMiddlePointReached = false;
        for (String deliveryId : deliveryValues.keySet()) {
            deliveryWiseRewardsDistributions.put(deliveryId, multiplier * (i++));
            if (deliveryValues.size() % 2 == 0 &&
                    i == (deliveryValues.size() / 2) &&
                    !isMiddlePointReached) {
                i = i - 1;
                isMiddlePointReached = true;
            }
        }
        context.setRewardPointsDistribution(deliveryWiseRewardsDistributions);
    }

    private double findMultiplier(int size, double rewardPoints) {
        double multiplier = 0;
        int middlePoint = size / 2;
        multiplier = rewardPoints / size;
        if (size % 2 == 0) {
            multiplier = multiplier / (middlePoint - 1);
        } else {
            multiplier = multiplier / middlePoint;
        }

        return multiplier;
    }
}
