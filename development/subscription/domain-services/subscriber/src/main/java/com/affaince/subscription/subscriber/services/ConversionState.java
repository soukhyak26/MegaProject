package com.affaince.subscription.subscriber.services;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class ConversionState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public ConversionState (BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
