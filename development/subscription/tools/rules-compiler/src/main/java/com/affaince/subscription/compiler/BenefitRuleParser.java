package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;
import com.affaince.subscription.common.type.DiscountUnit;
import org.antlr.v4.runtime.misc.NotNull;

public class BenefitRuleParser extends BenefitsRulesSetGrammarBaseListener {

    private RuleSet ruleSet = null;
    private Rule rule = null;

    private Conclusion applicability = new Conclusion();
    private MoneyConversion moneyConversion = new MoneyConversion();
    private PeriodConversion periodConversion = new PeriodConversion();
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
        rule.setApplicability(applicability);
    }

    @Override
    public void exitMoney_convert_expr(BenefitsRulesSetGrammarParser.Money_convert_exprContext ctx) {
        rule.setMoneyConversion(moneyConversion);
    }

    @Override
    public void exitMoney_expr_name(BenefitsRulesSetGrammarParser.Money_expr_nameContext ctx) {
        moneyConversion.setMoneyConvExprName(ctx.getText());
    }

    @Override
    public void exitCurrency_value(BenefitsRulesSetGrammarParser.Currency_valueContext ctx) {
        moneyConversion.setCurrencyValue(Integer.parseInt(ctx.getText()));
    }

    @Override
    public void exitPoint_value(BenefitsRulesSetGrammarParser.Point_valueContext ctx) {
        moneyConversion.setPointValue(Float.parseFloat(ctx.getText()));
    }

    @Override
    public void exitPeriod_convert_expr(BenefitsRulesSetGrammarParser.Period_convert_exprContext ctx) {
        rule.setPeriodConversion(periodConversion);
    }

    @Override
    public void exitPeriod_expr_name(BenefitsRulesSetGrammarParser.Period_expr_nameContext ctx) {
        periodConversion.setPeriodConvExprName(ctx.getText());
    }

    @Override
    public void exitPeriod_value(BenefitsRulesSetGrammarParser.Period_valueContext ctx) {
        periodConversion.setPeriodValue(Integer.parseInt(ctx.getText()));
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

    @Override
    public void exitWhich_delivey(BenefitsRulesSetGrammarParser.Which_deliveyContext ctx) {
        applicability.setBenefitPaymentFrequency(ctx.getText());
    }

    @Override
    public void exitOption(BenefitsRulesSetGrammarParser.OptionContext ctx) {
        applicability.setPeriodOption(ctx.getText());
    }

    @Override
    public void exitPayment_percent(BenefitsRulesSetGrammarParser.Payment_percentContext ctx) {
        applicability.setAdvancePaymentPercentage(Short.parseShort(ctx.getText()));
    }

    @Override
    public void exitOffer_expr(BenefitsRulesSetGrammarParser.Offer_exprContext ctx) {
        rule.setOffer(offer);
    }

    @Override
    public void exitOffer_point_value(BenefitsRulesSetGrammarParser.Offer_point_valueContext ctx) {
        offer.setOfferPointValue(Double.parseDouble(ctx.getText()));
    }

    @Override
    public void exitOfferedBenefitValue(BenefitsRulesSetGrammarParser.OfferedBenefitValueContext ctx) {
        offer.setOfferedBenefitValue(Double.parseDouble(ctx.getText()));
    }

    @Override
    public void exitOffered_benefit_type(BenefitsRulesSetGrammarParser.Offered_benefit_typeContext ctx) {
        offer.setOfferedBenefitType(DiscountUnit.valueOf(ctx.getText().toUpperCase()));
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
}
