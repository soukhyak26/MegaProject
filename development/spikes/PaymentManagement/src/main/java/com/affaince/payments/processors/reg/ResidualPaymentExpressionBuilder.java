package com.affaince.payments.processors.reg;

import com.affaince.payments.expressions.*;
import com.affaince.payments.grammer.PaymentsParser;
import com.affaince.payments.scheme.Scheme;
import com.affaince.payments.scheme.expressions.ResidualPaymentExpression;
import com.affaince.payments.scheme.expressions.VestingPeriodicityExpression;
import com.affaince.payments.vo.VestingDistribution;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class ResidualPaymentExpressionBuilder {
    private ExpressionBuilder expressionBuilder;
    private Scheme scheme;

    public ResidualPaymentExpressionBuilder(Scheme scheme){
        this.scheme=scheme;
    }

    public ResidualPaymentExpression buildResidualPaymentExpression(PaymentsParser.ResidualPayUnitContext residualPayUnitContext) {
        expressionBuilder = new ExpressionBuilder(scheme);
        ResidualPaymentExpression residualPaymentExpression = new ResidualPaymentExpression();
        //
        List<PaymentsParser.BlockStatementContext> blockStatementContexts =residualPayUnitContext.computeBlock().block().blockStatement();
        for(PaymentsParser.BlockStatementContext blockStatementContext:blockStatementContexts){
            residualPaymentExpression.addExpression(expressionBuilder.buildExpression(blockStatementContext.statement().statementExpression().expression()));
        }
        //
        residualPaymentExpression.setPrecedence(buildPaymentPrecedence(residualPayUnitContext));
        residualPaymentExpression.setVestingPeriodicityExpressions(buildVestingPeriodicityExpressions(residualPayUnitContext,residualPaymentExpression));
        residualPaymentExpression.setVestingDistributions(buildVestingDistributionExpressions(residualPayUnitContext,residualPaymentExpression));
        return residualPaymentExpression;
    }

    private PaymentPrecedence buildPaymentPrecedence(PaymentsParser.ResidualPayUnitContext residualPayUnitContext) {
        if (null != residualPayUnitContext.payBefore()) {
            return PaymentPrecedence.BEFORE;
        } else {
            return PaymentPrecedence.AFTER;
        }
    }

    private List<Expression> buildVestingPeriodicityExpressions(PaymentsParser.ResidualPayUnitContext residualPayUnitContext, ResidualPaymentExpression residualPaymentExpression) {
        List<PaymentsParser.VariableNameContext> variableNames = residualPayUnitContext.variableName();
        List<PaymentsParser.ExpressionContext> expressionContexts =residualPayUnitContext.expression();
        List<Expression> vestingExpressions = new ArrayList<>();
        for(int i=0;i< variableNames.size();i++){
            VestingPeriodicityExpression vestingPeriodicityExpression = new VestingPeriodicityExpression();
            Expression multiplierVariable = buildMultiplierVariable(variableNames.get(i).getText());
            vestingPeriodicityExpression.setMultiplierVariable(multiplierVariable);
            Expression expression = expressionBuilder.buildExpression(expressionContexts.get(i));
            vestingExpressions.add(new ArithmeticExpression(ArithmeticOperator.MULTIPLICATION, expression, multiplierVariable));
        }
        return vestingExpressions;
    }

    private Expression buildMultiplierVariable(String variableName) {
        return scheme.searchVariableExpression(variableName);
    }


    private List<VestingDistribution> buildVestingDistributionExpressions(PaymentsParser.ResidualPayUnitContext residualPayUnitContext, ResidualPaymentExpression residualPaymentExpression) {
        PaymentsParser.ProportionExpressionContext proportionExpressionContext = residualPayUnitContext.proportionExpression();
        int vestingCount = residualPaymentExpression.getVestingPeriodicityExpressions().size();
        List<VestingDistribution> deliveryWiseDistributionExpressions = new ArrayList<>();

        if (null != proportionExpressionContext.DEFAULT()) {
            for (int i =0; i < vestingCount; i++) {
                Expression vestingPeriodicityExpression = residualPaymentExpression.getVestingPeriodicityExpressions().get(i);
                Expression expression = new ArithmeticExpression(ArithmeticOperator.MULTIPLICATION,
                        new ArithmeticExpression(ArithmeticOperator.DIVISION, new UnaryExpression(1,UnaryType.NUMBER), new UnaryExpression(vestingCount,UnaryType.NUMBER)),
                        residualPaymentExpression.searchOutputVariableInExpressionQueue("RESIDUAL_DUE_AMOUNT"));
                deliveryWiseDistributionExpressions.add(new VestingDistribution(vestingPeriodicityExpression,expression));
            }
        } else {
            List<TerminalNode> proportionNumbers = residualPayUnitContext.proportionExpression().nonDefaultProportionExpression().NUMBER();
            int proportionSum = proportionNumbers.stream().map(token ->Integer.parseInt(token.getText())).reduce(Integer::sum).get();
            for (int i = 0; i < vestingCount; i++) {
                Expression vestingPeriodicityExpression = residualPaymentExpression.getVestingPeriodicityExpressions().get(i);
                Expression expression = new ArithmeticExpression(ArithmeticOperator.MULTIPLICATION,
                        new ArithmeticExpression(ArithmeticOperator.DIVISION, new UnaryExpression(Integer.parseInt(proportionNumbers.get(i).getText()),UnaryType.NUMBER), new UnaryExpression(proportionSum,UnaryType.NUMBER)),
                        residualPaymentExpression.searchOutputVariableInExpressionQueue("RESIDUAL_DUE_AMOUNT"));
                deliveryWiseDistributionExpressions.add(new VestingDistribution(vestingPeriodicityExpression,expression));
            }
        }

        return deliveryWiseDistributionExpressions;
    }
}
