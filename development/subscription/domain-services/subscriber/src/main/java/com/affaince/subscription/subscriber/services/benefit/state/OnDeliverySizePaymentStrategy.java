package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public class OnDeliverySizePaymentStrategy implements PaymentStrategy {
    @Override
    public void distributeRewardPoints(BenefitExecutionContext context) {
        final Map<String, Double> deliveryValues = context.getRequest().getDeliveryAmounts();
        final double rewardPoints = context.getRewardPoints() - context.getRequest().getRewardPointAdjustment();
        final Map<String, Double> deliveryWiseRewardsDistributions = new HashMap<>(deliveryValues.size());
        double totalDeliveriesValue = deliveryValues.values().stream().mapToDouble(i -> i.doubleValue()).sum();
        for (String deliveryId : deliveryValues.keySet()) {
            deliveryWiseRewardsDistributions.put(deliveryId,
                    (rewardPoints * deliveryValues.get(deliveryId)) / totalDeliveriesValue);
        }
        context.setRewardPointsDistribution(deliveryWiseRewardsDistributions);
    }
}
