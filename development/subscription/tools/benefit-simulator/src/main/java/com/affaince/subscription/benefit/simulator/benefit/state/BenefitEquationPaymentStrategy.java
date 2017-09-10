package com.affaince.subscription.benefit.simulator.benefit.state;

import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.pojos.DeliveryExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BenefitEquationPaymentStrategy implements PaymentStrategy {
    @Override
    public void distributeRewardPoints(BenefitExecutionContext context) {
        final Map<String, Double> deliveryValues = context.getRequest().getDeliveryAmounts();
        final double rewardPoints = context.getRewardPoints() - context.getRequest().getRewardPointAdjustment();
        final Map<String, Double> deliveryWiseRewardsDistributions = new HashMap<>(deliveryValues.size());
        List <String> deliveryIds = new ArrayList<>(deliveryValues.keySet());
        final List <DeliveryExpression> deliveryExpressions =
                context.getApplicableBenefit().getBenefitDistributionParameters().getDeliveries();
        final List <Integer> proportionValues =
                context.getApplicableBenefit().getBenefitDistributionParameters().getProportionValues();
        for (int i=0; i<deliveryExpressions.size();i++) {
            DeliveryExpression deliveryExpression = deliveryExpressions.get(i);
            double deliveryCountBase = (double) deliveryExpression.getDividend() / deliveryExpression.getDivisor();
            Double deliveryCount = deliveryCountBase * deliveryValues.size();
            int finalDeliveryCount = deliveryCount.intValue();
            double finalRewardPoints;
            if (proportionValues != null) {
                finalRewardPoints = (rewardPoints * proportionValues.get(i)) / 10;
            } else {
                finalRewardPoints = (rewardPoints * proportionValues.get(i)) / 10;
            }
            deliveryWiseRewardsDistributions.put(deliveryIds.get(finalDeliveryCount), finalRewardPoints);

        }
        context.setRewardPointsDistribution(deliveryWiseRewardsDistributions);
    }
}
