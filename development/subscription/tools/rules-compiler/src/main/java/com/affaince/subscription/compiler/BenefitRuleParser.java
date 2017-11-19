package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;
import com.affaince.subscription.PaymentGrammarParser;
import com.affaince.subscription.pojos.BenefitDistributionParameters;
import com.affaince.subscription.pojos.DeliveryExpression;
import com.affaince.subscription.pojos.EligibilityParameter;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitRuleParser extends BenefitsRulesSetGrammarBaseListener {

    private RuleSet ruleSet = null;
    private Rule rule = null;

    private Conclusion applicability = new Conclusion();
    private Offer offer = new Offer();
    private BenefitDistributionParameters benefitDistributionParameters;
    private List <EligibilityParameter> eligibilityParameters = new ArrayList<>();

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
        this.benefitDistributionParameters = new BenefitDistributionParameters();
    }

    @Override
    public void exitConclusion(@NotNull BenefitsRulesSetGrammarParser.ConclusionContext ctx) {
        //rule.setApplicability(applicability);
        rule.setBenefitPaymentMethod (ctx.getText());
    }

    @Override
    public void exitSingle_rule(@NotNull BenefitsRulesSetGrammarParser.Single_ruleContext ctx) {
        this.ruleSet.addRule(this.rule);
        this.rule.setBenefitDistributionParameters(this.benefitDistributionParameters);
        this.rule.setEligibilityParameters(eligibilityParameters);
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
        String eligibilityCondition = ctx.logical_expr().getText();
        eligibilityCondition = eligibilityCondition.replace("or", " or ");
        eligibilityCondition = eligibilityCondition.replace("and", " and ");
        rule.setEligibilityCondition(eligibilityCondition);
    }

    @Override
    public void exitComparison_operand(BenefitsRulesSetGrammarParser.Comparison_operandContext ctx) {
        eligibilityParameters.add(EligibilityParameter.valueOf(ctx.getText().toUpperCase()));
    }

    private List<ParseTree> createList(List<ParseTree> parseTrees) {
        List<ParseTree> newList = new ArrayList<>();
        for (ParseTree parseTree : parseTrees) {
            if (parseTree.getChildCount() <= 1) {
                System.out.println(parseTree.getText());
            } else {
                newList.add(parseTree);
            }
        }
        return newList;
    }

    @Override public void exitConvert_expr(BenefitsRulesSetGrammarParser.Convert_exprContext ctx) {
        PointConversionParameters pointConversionParameters = new PointConversionParameters();
        pointConversionParameters.setSubscriptionValue(Double.parseDouble(ctx.subscription_value().getText()));
        pointConversionParameters.setPointValue(Double.parseDouble(ctx.point_value().getText()));
        pointConversionParameters.setSubscriptionPeriod(Double.parseDouble(ctx.period_value().getText()));
        rule.setPointConversionParameters(pointConversionParameters);
    }

    @Override
    public void exitDelivery_number_list(BenefitsRulesSetGrammarParser.Delivery_number_listContext ctx) {
        List<DeliveryExpression> deliveryCounts =
                Stream.of(ctx.getText().split(",")).
                        <DeliveryExpression>map(DeliveryExpression::create).collect(Collectors.toList());
        benefitDistributionParameters.setDeliveries(deliveryCounts);
    }

    @Override
    public void exitProportion_value(BenefitsRulesSetGrammarParser.Proportion_valueContext ctx) {
        if (!ctx.getText().equals("default")) {
            List<Integer> proportionValues =
                    Stream.of(ctx.getText().split(","))
                            .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            benefitDistributionParameters.setProportionValues(proportionValues);
        }
    }
}
