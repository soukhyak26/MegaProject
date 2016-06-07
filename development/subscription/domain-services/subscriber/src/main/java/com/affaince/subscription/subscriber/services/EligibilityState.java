package com.affaince.subscription.subscriber.services;

import com.affaince.subscription.compiler.BenefitCompiler;
import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.query.repository.BenefitViewRepository;
import com.affaince.subscription.subscriber.query.view.BenefitView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class EligibilityState implements BenefitCalculationState {

    @Autowired
    private BenefitViewRepository benefitViewRepository;
    private final BenefitCalculationState nextState;

    private static Logger logger = LoggerFactory.getLogger(EligibilityState.class);
    public EligibilityState (BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        Iterable<BenefitView> benefitViews = benefitViewRepository.findAll();

        BenefitCompiler benefitCompiler = new BenefitCompiler();
        for (BenefitView benefitView:benefitViews) {
            Rule rule = benefitCompiler.compile(benefitView.getBenefitEquation());
            if (evaluateBenefitCondition(rule.getEligibilityCondition())) {
                context.setApplicableBenefit (rule);
            }
        }

        if (nextState != null) {
            nextState.calculate(context);
        }
    }

    private boolean evaluateBenefitCondition(String condition) {

        return true;
    }
}
