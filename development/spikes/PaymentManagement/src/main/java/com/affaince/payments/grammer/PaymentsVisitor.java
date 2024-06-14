// Generated from Payments.g4 by ANTLR 4.13.0

package com.affaince.payments.grammer;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PaymentsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PaymentsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#scheme}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScheme(PaymentsParser.SchemeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#givenUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGivenUnit(PaymentsParser.GivenUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#givenBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGivenBody(PaymentsParser.GivenBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#computeUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputeUnit(PaymentsParser.ComputeUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#computeBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComputeBlock(PaymentsParser.ComputeBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#advancePayUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdvancePayUnit(PaymentsParser.AdvancePayUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#residualPayUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResidualPayUnit(PaymentsParser.ResidualPayUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#eligibilityUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEligibilityUnit(PaymentsParser.EligibilityUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#payBefore}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPayBefore(PaymentsParser.PayBeforeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#payAfter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPayAfter(PaymentsParser.PayAfterContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#payMultiplier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPayMultiplier(PaymentsParser.PayMultiplierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(PaymentsParser.VariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#variableDeclaratorId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaratorId(PaymentsParser.VariableDeclaratorIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(PaymentsParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(PaymentsParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#conditionalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpression(PaymentsParser.ConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#conditionalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalOrExpression(PaymentsParser.ConditionalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#conditionalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalAndExpression(PaymentsParser.ConditionalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#relationalOp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalOp(PaymentsParser.RelationalOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(PaymentsParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(PaymentsParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(PaymentsParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(PaymentsParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(PaymentsParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#variableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableName(PaymentsParser.VariableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#connectorOr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnectorOr(PaymentsParser.ConnectorOrContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(PaymentsParser.ParExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#nonDefaultProportionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonDefaultProportionExpression(PaymentsParser.NonDefaultProportionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#proportionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProportionExpression(PaymentsParser.ProportionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(PaymentsParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PaymentsParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(PaymentsParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#iterativeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterativeStatement(PaymentsParser.IterativeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#iterativeAggregationExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterativeAggregationExpression(PaymentsParser.IterativeAggregationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PaymentsParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#statementExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(PaymentsParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PaymentsParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(PaymentsParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PaymentsParser#connectorAnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConnectorAnd(PaymentsParser.ConnectorAndContext ctx);
}