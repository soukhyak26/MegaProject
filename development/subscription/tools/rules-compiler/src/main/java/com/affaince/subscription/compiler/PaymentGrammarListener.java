// Generated from com/affaince/subscription/PaymentGrammar.g4 by ANTLR 4.5
package com.affaince.subscription.compiler;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PaymentGrammarParser}.
 */
public interface PaymentGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#payment_rule_set}.
	 * @param ctx the parse tree
	 */
	void enterPayment_rule_set(PaymentGrammarParser.Payment_rule_setContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#payment_rule_set}.
	 * @param ctx the parse tree
	 */
	void exitPayment_rule_set(PaymentGrammarParser.Payment_rule_setContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#payment_rule}.
	 * @param ctx the parse tree
	 */
	void enterPayment_rule(PaymentGrammarParser.Payment_ruleContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#payment_rule}.
	 * @param ctx the parse tree
	 */
	void exitPayment_rule(PaymentGrammarParser.Payment_ruleContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#advance_payment_expr}.
	 * @param ctx the parse tree
	 */
	void enterAdvance_payment_expr(PaymentGrammarParser.Advance_payment_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#advance_payment_expr}.
	 * @param ctx the parse tree
	 */
	void exitAdvance_payment_expr(PaymentGrammarParser.Advance_payment_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#residual_due_payment_expr}.
	 * @param ctx the parse tree
	 */
	void enterResidual_due_payment_expr(PaymentGrammarParser.Residual_due_payment_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#residual_due_payment_expr}.
	 * @param ctx the parse tree
	 */
	void exitResidual_due_payment_expr(PaymentGrammarParser.Residual_due_payment_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#proportion_value}.
	 * @param ctx the parse tree
	 */
	void enterProportion_value(PaymentGrammarParser.Proportion_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#proportion_value}.
	 * @param ctx the parse tree
	 */
	void exitProportion_value(PaymentGrammarParser.Proportion_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#delivery_value}.
	 * @param ctx the parse tree
	 */
	void enterDelivery_value(PaymentGrammarParser.Delivery_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#delivery_value}.
	 * @param ctx the parse tree
	 */
	void exitDelivery_value(PaymentGrammarParser.Delivery_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#percent_source}.
	 * @param ctx the parse tree
	 */
	void enterPercent_source(PaymentGrammarParser.Percent_sourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#percent_source}.
	 * @param ctx the parse tree
	 */
	void exitPercent_source(PaymentGrammarParser.Percent_sourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#percent_value}.
	 * @param ctx the parse tree
	 */
	void enterPercent_value(PaymentGrammarParser.Percent_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#percent_value}.
	 * @param ctx the parse tree
	 */
	void exitPercent_value(PaymentGrammarParser.Percent_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#delivery_count}.
	 * @param ctx the parse tree
	 */
	void enterDelivery_count(PaymentGrammarParser.Delivery_countContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#delivery_count}.
	 * @param ctx the parse tree
	 */
	void exitDelivery_count(PaymentGrammarParser.Delivery_countContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#after_before}.
	 * @param ctx the parse tree
	 */
	void enterAfter_before(PaymentGrammarParser.After_beforeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#after_before}.
	 * @param ctx the parse tree
	 */
	void exitAfter_before(PaymentGrammarParser.After_beforeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#delivery_number_list}.
	 * @param ctx the parse tree
	 */
	void enterDelivery_number_list(PaymentGrammarParser.Delivery_number_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#delivery_number_list}.
	 * @param ctx the parse tree
	 */
	void exitDelivery_number_list(PaymentGrammarParser.Delivery_number_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link PaymentGrammarParser#delivery_number_expr}.
	 * @param ctx the parse tree
	 */
	void enterDelivery_number_expr(PaymentGrammarParser.Delivery_number_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link PaymentGrammarParser#delivery_number_expr}.
	 * @param ctx the parse tree
	 */
	void exitDelivery_number_expr(PaymentGrammarParser.Delivery_number_exprContext ctx);
}