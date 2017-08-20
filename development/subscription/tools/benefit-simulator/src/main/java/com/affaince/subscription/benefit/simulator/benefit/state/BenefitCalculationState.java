package com.affaince.subscription.benefit.simulator.benefit.state;


import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public interface BenefitCalculationState {
    void calculate(BenefitExecutionContext context);
}
