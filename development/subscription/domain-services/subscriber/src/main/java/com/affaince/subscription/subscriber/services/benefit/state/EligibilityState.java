package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.query.repository.BenefitViewRepository;
import com.affaince.subscription.subscriber.query.view.BenefitView;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.io.IOException;

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

        final Iterable<BenefitView> benefitViews = benefitViewRepository.findAll();
        final ObjectMapper objectMapper = new ObjectMapper();
        for (BenefitView benefitView:benefitViews) {
            Rule rule = null;
            try {
                rule = objectMapper.readValue(benefitView.getBenefitEquationInJsonFormat(), Rule.class);
            } catch (IOException e) {
                logger.info("Cannot parse benefit rules from json to object: " + benefitView.getBenefitEquationInJsonFormat());
            }
            if (evaluateBenefitCondition(rule.getEligibilityCondition(), context)) {
                context.setApplicableBenefit (rule);
                if (nextState != null) {
                    nextState.calculate(context);
                }
            }
        }
    }

    private boolean evaluateBenefitCondition(String condition, BenefitExecutionContext benefitExecutionContext) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression1 = expressionParser.parseExpression(condition);
        EvaluationContext context = new StandardEvaluationContext(benefitExecutionContext.getRequest());
        boolean result = expression1.getValue(context, Boolean.class);
        return true;
    }
}
