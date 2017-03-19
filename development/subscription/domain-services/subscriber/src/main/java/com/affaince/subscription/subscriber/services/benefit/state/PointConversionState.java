package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.compiler.PointConversionParameters;
import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitCalculationRequest;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class PointConversionState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public PointConversionState(BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        final Rule rule = context.getApplicableBenefit();
        final BenefitCalculationRequest request = context.getRequest();
        PointConversionParameters pointCalculationParameters =
                rule.getPointConversionParameters();
        pointCalculationParameters.setCurrentSubscriptionAmount (request.getCurrentSubscriptionAmount());
        pointCalculationParameters.setCurrentSubscriptionPeriod(request.getCurrentSubscriptionPeriod());
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(rule.getPointConversionExpression());
        EvaluationContext evaluationContext = new StandardEvaluationContext(pointCalculationParameters);
        double rewardPoints = expression.getValue(evaluationContext, Double.class);
        context.addRewardPoints (rewardPoints);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
