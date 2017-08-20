package com.affaince.subscription.benefit.simulator.benefit.state;

import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.compiler.Rule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class ApplicabilityState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public ApplicabilityState (BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        final Rule rule = context.getApplicableBenefit();
        context.setBenefitPayMethod(rule.getBenefitPaymentMethod());
        Map<String, Double> deliveryWiseRewardsDistributions = new HashMap<>(
                context.getRequest().getDeliveryAmounts().size()
        );
        if (rule.getBenefitPaymentMethod().equals("incremental")) {
            PaymentStrategy paymentStrategy = new IncrementalPaymentStrategy();
            deliveryWiseRewardsDistributions = paymentStrategy.distributeRewardPoints(context.getRequest().getDeliveryAmounts(),
                    context.getRewardPoints() - context.getRequest().getRewardPointAdjustment());
        } else if (rule.getBenefitPaymentMethod().equals("on_delivery_size")) {
            PaymentStrategy paymentStrategy = new OnDeliverySizePaymentStrategy();
            deliveryWiseRewardsDistributions = paymentStrategy.distributeRewardPoints(context.getRequest().getDeliveryAmounts(),
                    context.getRewardPoints() - context.getRequest().getRewardPointAdjustment());
        }
        context.setRewardPointsDistribution(deliveryWiseRewardsDistributions);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
