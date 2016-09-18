package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

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
                    context.getRewardPoints());
        } else if (rule.getBenefitPaymentMethod().equals("on_delivery_size")) {
            PaymentStrategy paymentStrategy = new OnDeliverySizePaymentStrategy();
            deliveryWiseRewardsDistributions = paymentStrategy.distributeRewardPoints(context.getRequest().getDeliveryAmounts(),
                    context.getRewardPoints());
        }
        context.setRewardPointsDistribution(deliveryWiseRewardsDistributions);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
