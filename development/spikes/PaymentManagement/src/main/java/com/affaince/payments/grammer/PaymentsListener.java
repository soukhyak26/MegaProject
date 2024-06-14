// Generated from Payments.g4 by ANTLR 4.13.0

package com.affaince.payments.grammer;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PaymentsParser}.
 */
public interface PaymentsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#scheme}.
	 * @param ctx the parse tree
	 */
	void enterScheme(PaymentsParser.SchemeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#scheme}.
	 * @param ctx the parse tree
	 */
	void exitScheme(PaymentsParser.SchemeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#givenUnit}.
	 * @param ctx the parse tree
	 */
	void enterGivenUnit(PaymentsParser.GivenUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#givenUnit}.
	 * @param ctx the parse tree
	 */
	void exitGivenUnit(PaymentsParser.GivenUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#givenBody}.
	 * @param ctx the parse tree
	 */
	void enterGivenBody(PaymentsParser.GivenBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#givenBody}.
	 * @param ctx the parse tree
	 */
	void exitGivenBody(PaymentsParser.GivenBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#computeUnit}.
	 * @param ctx the parse tree
	 */
	void enterComputeUnit(PaymentsParser.ComputeUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#computeUnit}.
	 * @param ctx the parse tree
	 */
	void exitComputeUnit(PaymentsParser.ComputeUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#computeBlock}.
	 * @param ctx the parse tree
	 */
	void enterComputeBlock(PaymentsParser.ComputeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#computeBlock}.
	 * @param ctx the parse tree
	 */
	void exitComputeBlock(PaymentsParser.ComputeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#advancePayUnit}.
	 * @param ctx the parse tree
	 */
	void enterAdvancePayUnit(PaymentsParser.AdvancePayUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#advancePayUnit}.
	 * @param ctx the parse tree
	 */
	void exitAdvancePayUnit(PaymentsParser.AdvancePayUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#residualPayUnit}.
	 * @param ctx the parse tree
	 */
	void enterResidualPayUnit(PaymentsParser.ResidualPayUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#residualPayUnit}.
	 * @param ctx the parse tree
	 */
	void exitResidualPayUnit(PaymentsParser.ResidualPayUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#eligibilityUnit}.
	 * @param ctx the parse tree
	 */
	void enterEligibilityUnit(PaymentsParser.EligibilityUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#eligibilityUnit}.
	 * @param ctx the parse tree
	 */
	void exitEligibilityUnit(PaymentsParser.EligibilityUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#payBefore}.
	 * @param ctx the parse tree
	 */
	void enterPayBefore(PaymentsParser.PayBeforeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#payBefore}.
	 * @param ctx the parse tree
	 */
	void exitPayBefore(PaymentsParser.PayBeforeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#payAfter}.
	 * @param ctx the parse tree
	 */
	void enterPayAfter(PaymentsParser.PayAfterContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#payAfter}.
	 * @param ctx the parse tree
	 */
	void exitPayAfter(PaymentsParser.PayAfterContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#payMultiplier}.
	 * @param ctx the parse tree
	 */
	void enterPayMultiplier(PaymentsParser.PayMultiplierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#payMultiplier}.
	 * @param ctx the parse tree
	 */
	void exitPayMultiplier(PaymentsParser.PayMultiplierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclarationStatement(PaymentsParser.VariableDeclarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclarationStatement(PaymentsParser.VariableDeclarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaratorId(PaymentsParser.VariableDeclaratorIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaratorId(PaymentsParser.VariableDeclaratorIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void enterVariableInitializer(PaymentsParser.VariableInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#variableInitializer}.
	 * @param ctx the parse tree
	 */
	void exitVariableInitializer(PaymentsParser.VariableInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void enterArrayInitializer(PaymentsParser.ArrayInitializerContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#arrayInitializer}.
	 * @param ctx the parse tree
	 */
	void exitArrayInitializer(PaymentsParser.ArrayInitializerContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalExpression(PaymentsParser.ConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#conditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalExpression(PaymentsParser.ConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalOrExpression(PaymentsParser.ConditionalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalOrExpression(PaymentsParser.ConditionalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterConditionalAndExpression(PaymentsParser.ConditionalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitConditionalAndExpression(PaymentsParser.ConditionalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#relationalOp}.
	 * @param ctx the parse tree
	 */
	void enterRelationalOp(PaymentsParser.RelationalOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#relationalOp}.
	 * @param ctx the parse tree
	 */
	void exitRelationalOp(PaymentsParser.RelationalOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(PaymentsParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(PaymentsParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(PaymentsParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(PaymentsParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(PaymentsParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(PaymentsParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(PaymentsParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(PaymentsParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(PaymentsParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(PaymentsParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#variableName}.
	 * @param ctx the parse tree
	 */
	void enterVariableName(PaymentsParser.VariableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#variableName}.
	 * @param ctx the parse tree
	 */
	void exitVariableName(PaymentsParser.VariableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#connectorOr}.
	 * @param ctx the parse tree
	 */
	void enterConnectorOr(PaymentsParser.ConnectorOrContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#connectorOr}.
	 * @param ctx the parse tree
	 */
	void exitConnectorOr(PaymentsParser.ConnectorOrContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void enterParExpression(PaymentsParser.ParExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#parExpression}.
	 * @param ctx the parse tree
	 */
	void exitParExpression(PaymentsParser.ParExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#nonDefaultProportionExpression}.
	 * @param ctx the parse tree
	 */
	void enterNonDefaultProportionExpression(PaymentsParser.NonDefaultProportionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#nonDefaultProportionExpression}.
	 * @param ctx the parse tree
	 */
	void exitNonDefaultProportionExpression(PaymentsParser.NonDefaultProportionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#proportionExpression}.
	 * @param ctx the parse tree
	 */
	void enterProportionExpression(PaymentsParser.ProportionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#proportionExpression}.
	 * @param ctx the parse tree
	 */
	void exitProportionExpression(PaymentsParser.ProportionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(PaymentsParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(PaymentsParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PaymentsParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PaymentsParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(PaymentsParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(PaymentsParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#iterativeStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterativeStatement(PaymentsParser.IterativeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#iterativeStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterativeStatement(PaymentsParser.IterativeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#iterativeAggregationExpression}.
	 * @param ctx the parse tree
	 */
	void enterIterativeAggregationExpression(PaymentsParser.IterativeAggregationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#iterativeAggregationExpression}.
	 * @param ctx the parse tree
	 */
	void exitIterativeAggregationExpression(PaymentsParser.IterativeAggregationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PaymentsParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PaymentsParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(PaymentsParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#statementExpression}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(PaymentsParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(PaymentsParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(PaymentsParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(PaymentsParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(PaymentsParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentsParser#connectorAnd}.
	 * @param ctx the parse tree
	 */
	void enterConnectorAnd(PaymentsParser.ConnectorAndContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentsParser#connectorAnd}.
	 * @param ctx the parse tree
	 */
	void exitConnectorAnd(PaymentsParser.ConnectorAndContext ctx);
}