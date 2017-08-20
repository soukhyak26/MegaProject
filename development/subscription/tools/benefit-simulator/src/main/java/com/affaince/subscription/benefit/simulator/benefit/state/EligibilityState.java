package com.affaince.subscription.benefit.simulator.benefit.state;

import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.compiler.BenefitCompiler;
import com.affaince.subscription.compiler.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

   private BenefitCalculationState nextState;

    private static Logger logger = LoggerFactory.getLogger(EligibilityState.class);
    public EligibilityState (BenefitCalculationState state) {
        this.nextState=state;
    }

    public EligibilityState() {
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

            Rule rule = null;
        BenefitCompiler benefitCompiler = new BenefitCompiler();
        rule = benefitCompiler.compile(context.getRequest().getBenefitEquation());
        if (evaluateBenefitCondition(rule.getEligibilityCondition(), context)) {
                context.setApplicableBenefit (rule);
                if (nextState != null) {
                    nextState.calculate(context);
                }
            }

    }

    private boolean evaluateBenefitCondition(String condition, BenefitExecutionContext benefitExecutionContext) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression1 = expressionParser.parseExpression(condition);
        EvaluationContext context = new StandardEvaluationContext(benefitExecutionContext.getRequest());
        boolean result = (boolean)expression1.getValue(context);
        return result;
    }
}