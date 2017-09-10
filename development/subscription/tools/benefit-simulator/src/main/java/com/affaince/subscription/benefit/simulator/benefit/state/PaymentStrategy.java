package com.affaince.subscription.benefit.simulator.benefit.state;


import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public interface PaymentStrategy {
    void distributeRewardPoints(BenefitExecutionContext context);
}
