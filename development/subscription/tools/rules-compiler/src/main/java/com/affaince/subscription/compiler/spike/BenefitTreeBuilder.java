package com.affaince.subscription.compiler.spike;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;

public class BenefitTreeBuilder extends BenefitsRulesSetGrammarBaseListener {

    /*private RuleSet ruleSet = null;
    private Rule rule = null;
    private LogicalExpression condition = null;

    private Stack<LogicalExpression> logicalExpressions = new Stack<>();
    private Stack<ComparisonOperand> comparisonOperands = new Stack<>();
    private Stack<ArithmeticExpression> arithmeticExpressions = new Stack<>();
    private Conclusion conclusion = new Conclusion();
    private MoneyConversion moneyConversion = new MoneyConversion();
    private PeriodConversion periodConversion = new PeriodConversion();
    private Offer offer = new Offer();

    public RuleSet getRuleSet() {
        return ruleSet;
    }

    public Stack<LogicalExpression> getLogicalExpressions() {
        return logicalExpressions;
    }

    @Override
    public void enterRule_set(@NotNull BenefitsRulesSetGrammarParser.Rule_setContext ctx) {
        assert ruleSet == null;
        assert rule == null;
        assert condition == null;

        assert logicalExpressions.empty();
        assert comparisonOperands.empty();
        assert arithmeticExpressions.empty();

        this.ruleSet = new RuleSet();
    }

    @Override
    public void enterSingle_rule(@NotNull BenefitsRulesSetGrammarParser.Single_ruleContext ctx) {
        this.rule = new Rule();
    }

    @Override
    public void exitConclusion(@NotNull BenefitsRulesSetGrammarParser.ConclusionContext ctx) {
        rule.setConclusion(conclusion);
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
        this.rule.setCondition(this.logicalExpressions.pop());
        this.rule.setArithmeticExpression(this.arithmeticExpressions.pop());
        this.ruleSet.addRule(this.rule);
        this.rule = null;
    }

    @Override
    public void exitNumericVariable(@NotNull BenefitsRulesSetGrammarParser.NumericVariableContext ctx) {
        this.arithmeticExpressions.add(new NumericVariable(ctx.getText()));
    }

    @Override
    public void exitNumericConst(@NotNull BenefitsRulesSetGrammarParser.NumericConstContext ctx) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "#0.0#";

        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal value;
        try {
            value = (BigDecimal) decimalFormat.parse(ctx.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.arithmeticExpressions.push(new NumericConstant(value));
    }

    @Override
    public void enterNumericConst(@NotNull BenefitsRulesSetGrammarParser.NumericConstContext ctx) {
        super.enterNumericConst(ctx);
    }

    @Override
    public void exitArithmeticExpressionMult(@NotNull BenefitsRulesSetGrammarParser.ArithmeticExpressionMultContext ctx) {
        exitRealArithmeticExpression("*");
    }

    @Override
    public void exitArithmeticExpressionDiv(@NotNull BenefitsRulesSetGrammarParser.ArithmeticExpressionDivContext ctx) {
        exitRealArithmeticExpression("/");
    }

    @Override
    public void exitArithmeticExpressionPlus(@NotNull BenefitsRulesSetGrammarParser.ArithmeticExpressionPlusContext ctx) {
        exitRealArithmeticExpression("+");
    }

    @Override
    public void exitArithmeticExpressionMinus(@NotNull BenefitsRulesSetGrammarParser.ArithmeticExpressionMinusContext ctx) {
        exitRealArithmeticExpression("-");
    }

    protected void exitRealArithmeticExpression(String op) {
        // popping order matters
        ArithmeticExpression right = this.arithmeticExpressions.pop();
        ArithmeticExpression left = this.arithmeticExpressions.pop();
        RealArithmeticExpression expr = new RealArithmeticExpression(op, left, right);
        this.arithmeticExpressions.push(expr);
    }

    @Override
    public void exitArithmeticExpressionNegation(
            @NotNull BenefitsRulesSetGrammarParser.ArithmeticExpressionNegationContext ctx) {
        Negation negation = new Negation(this.arithmeticExpressions.pop());
        this.arithmeticExpressions.push(negation);
    }

    @Override
    public void exitComparison_operand(@NotNull BenefitsRulesSetGrammarParser.Comparison_operandContext ctx) {
//        ArithmeticExpression expr = this.arithmeticExpressions.pop();
   //     this.comparisonOperands.push(expr);
        this.comparisonOperands.push(new NumericVariable(ctx.getText()));
    }

    @Override public void exitComparison_constant(BenefitsRulesSetGrammarParser.Comparison_constantContext ctx) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "#0.0#";
        BigDecimal value = new BigDecimal(0.0);
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        try {
            value = (BigDecimal) decimalFormat.parse(ctx.getText());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.comparisonOperands.push(new NumericConstant(value));
    }

    @Override
    public void exitComparisonExpressionWithOperator(
            @NotNull BenefitsRulesSetGrammarParser.ComparisonExpressionWithOperatorContext ctx) {
        // popping order matters
        ComparisonOperand right = this.comparisonOperands.pop();
        ComparisonOperand left = this.comparisonOperands.pop();
        String op = ctx.getChild(1).getText();
        ComparisonExpression expr = new ComparisonExpression(op, left, right);
        this.logicalExpressions.push(expr);
    }

    @Override
    public void exitLogicalConst(@NotNull BenefitsRulesSetGrammarParser.LogicalConstContext ctx) {
        switch (ctx.getText().toUpperCase()) {
            case "TRUE":
                this.logicalExpressions.push(LogicalConstant.getTrue());
                break;
            case "FALSE":
                this.logicalExpressions.push(LogicalConstant.getFalse());
                break;
            default:
                throw new RuntimeException("Unknown logical constant: " + ctx.getText());
        }
    }

    @Override
    public void exitLogicalVariable(@NotNull BenefitsRulesSetGrammarParser.LogicalVariableContext ctx) {
        LogicalVariable variable = new LogicalVariable(ctx.getText());
        this.logicalExpressions.push(variable);
    }

    @Override
    public void exitLogicalExpressionOr(@NotNull BenefitsRulesSetGrammarParser.LogicalExpressionOrContext ctx) {
        // popping order matters
        LogicalExpression right = logicalExpressions.pop();
        LogicalExpression left = logicalExpressions.pop();
        OrExpression expr = new OrExpression(left, right);
        this.logicalExpressions.push(expr);
    }

    @Override
    public void exitLogicalExpressionAnd(@NotNull BenefitsRulesSetGrammarParser.LogicalExpressionAndContext ctx) {
        // popping order matters
        LogicalExpression right = logicalExpressions.pop();
        LogicalExpression left = logicalExpressions.pop();
        AndExpression expr = new AndExpression(left, right);
        this.logicalExpressions.push(expr);
    }

    @Override
    public void exitBenefit_pay_method(BenefitsRulesSetGrammarParser.Benefit_pay_methodContext ctx) {
        conclusion.setBenefitPayMethod(ctx.getText());
    }

    @Override
    public void exitWhich_delivey(BenefitsRulesSetGrammarParser.Which_deliveyContext ctx) {
        conclusion.setBenefitPaymentFrequency(ctx.getText());
    }

    @Override
    public void exitOption(BenefitsRulesSetGrammarParser.OptionContext ctx) {
        conclusion.setPeriodOption(ctx.getText());
    }

    @Override
    public void exitPayment_percent(BenefitsRulesSetGrammarParser.Payment_percentContext ctx) {
        conclusion.setAdvancePaymentPercentage(Short.parseShort(ctx.getText()));
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
    }*/
}
