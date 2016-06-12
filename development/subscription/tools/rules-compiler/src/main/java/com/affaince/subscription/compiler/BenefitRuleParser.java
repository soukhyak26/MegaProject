package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;
import org.antlr.v4.runtime.misc.NotNull;

public class BenefitRuleParser extends BenefitsRulesSetGrammarBaseListener {

    private RuleSet ruleSet = null;
    private Rule rule = null;

    private Conclusion applicability = new Conclusion();
    private Offer offer = new Offer();

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    @Override
    public void enterRule_set(@NotNull BenefitsRulesSetGrammarParser.Rule_setContext ctx) {
        assert ruleSet == null;
        assert rule == null;

        this.ruleSet = new RuleSet();
    }

    @Override
    public void enterSingle_rule(@NotNull BenefitsRulesSetGrammarParser.Single_ruleContext ctx) {
        this.rule = new Rule();
    }

    @Override
    public void exitConclusion(@NotNull BenefitsRulesSetGrammarParser.ConclusionContext ctx) {
        //rule.setApplicability(applicability);
        rule.setBenefitPaymentMethod (ctx.getText());
    }

    @Override
    public void exitSingle_rule(@NotNull BenefitsRulesSetGrammarParser.Single_ruleContext ctx) {
        this.ruleSet.addRule(this.rule);
        this.rule = null;
    }


    @Override
    public void exitBenefit_pay_method(BenefitsRulesSetGrammarParser.Benefit_pay_methodContext ctx) {
        applicability.setBenefitPayMethod(ctx.getText());
    }

    @Override public void exitArithmetic_expression(BenefitsRulesSetGrammarParser.Arithmetic_expressionContext ctx) {
        rule.setPointConversionExpression(ctx.getText());
    }

    @Override
    public void exitEligibility_condition(BenefitsRulesSetGrammarParser.Eligibility_conditionContext ctx) {
        /*String eligibilityCondition = "";
        for (TerminalNode terminalNode: ctx.){
            eligibilityCondition = eligibilityCondition + terminalNode.getText();
        }*/
        rule.setEligibilityCondition(ctx.getText());
    }

    @Override public void exitConvert_expr(BenefitsRulesSetGrammarParser.Convert_exprContext ctx) {
        PointConversionParameters pointConversionParameters = new PointConversionParameters();
        pointConversionParameters.setSubscriptionValue(Double.parseDouble(ctx.subscription_value().getText()));
        pointConversionParameters.setPointValue(Double.parseDouble(ctx.point_value().getText()));
        pointConversionParameters.setSubscriptionPeriod(Double.parseDouble(ctx.period_value().getText()));
        rule.setPointConversionParameters(pointConversionParameters);
    }
}
