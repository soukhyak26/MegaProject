package com.affaince.subscription.compiler;

import com.affaince.subscription.common.type.PaymentEvent;
import com.affaince.subscription.common.type.PaymentSource;
import com.affaince.subscription.common.type.TotalDeliveryBase;
import com.affaince.subscription.pojos.AdvancePaymentParameters;
import com.affaince.subscription.pojos.DeliveryExpression;
import com.affaince.subscription.pojos.PaymentExpression;
import com.affaince.subscription.pojos.ResidualDuePaymentParameters;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import static com.affaince.subscription.common.type.PaymentSource.*;

/**
 * Created by rahul on 14/7/17.
 */
public class PaymentRuleParser extends PaymentGrammarBaseListener {

    private PaymentExpression paymentExpression;
    private AdvancePaymentParameters advancePaymentParameters;
    private ResidualDuePaymentParameters residualDuePaymentParameters;

    public PaymentExpression getPaymentExpression() {
        return paymentExpression;
    }

    @Override public void enterPayment_rule_set(PaymentGrammarParser.Payment_rule_setContext ctx) {
        
    }

    @Override public void exitPayment_rule_set(PaymentGrammarParser.Payment_rule_setContext ctx) { 
        
    }
    
    @Override public void enterPayment_rule(PaymentGrammarParser.Payment_ruleContext ctx) {
        
    }
    
    @Override public void exitPayment_rule(PaymentGrammarParser.Payment_ruleContext ctx) {
        paymentExpression = new PaymentExpression(advancePaymentParameters, residualDuePaymentParameters);
    }
    
    @Override public void enterAdvance_payment_expr(PaymentGrammarParser.Advance_payment_exprContext ctx) {
        advancePaymentParameters = new AdvancePaymentParameters();
    }
    
    @Override public void exitAdvance_payment_expr(PaymentGrammarParser.Advance_payment_exprContext ctx) { }

    @Override public void exitPayment_event(PaymentGrammarParser.Payment_eventContext ctx) {
        advancePaymentParameters.setPaymentEvent(PaymentEvent.valueOf
                (ctx.getText().replace(" ", "_").toUpperCase()));
    }
    
    @Override public void enterResidual_due_payment_expr(PaymentGrammarParser.Residual_due_payment_exprContext ctx) {
        residualDuePaymentParameters = new ResidualDuePaymentParameters();
    }
    
    @Override public void exitResidual_due_payment_expr(PaymentGrammarParser.Residual_due_payment_exprContext ctx) { }
    
    @Override public void enterProportion_value(PaymentGrammarParser.Proportion_valueContext ctx) { }
    
    @Override public void exitProportion_value(PaymentGrammarParser.Proportion_valueContext ctx) { }
    
    @Override public void exitPercent_source(PaymentGrammarParser.Percent_sourceContext ctx) {
        if (ctx.getChildCount() == 1) {
            advancePaymentParameters.setPaymentSource(CURRENT_SUBSCRIPTION_AMOUNT);
        }
        if (ctx.getChildCount() > 1) {
           advancePaymentParameters.setPaymentSource(DELIVERY);
            final DeliveryExpression deliveryExpression = new DeliveryExpression(TotalDeliveryBase.N,
                    Integer.parseInt(ctx.getChild(0).getText()), Integer.parseInt(ctx.getChild(2).getText()));
            advancePaymentParameters.setDeliveryExpression(deliveryExpression);
        }
    }

    @Override public void exitPercent_value(PaymentGrammarParser.Percent_valueContext ctx) {
        advancePaymentParameters.setPercentValue(Double.parseDouble(ctx.getText()));
    }

    @Override public void enterAfter_before(PaymentGrammarParser.After_beforeContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitAfter_before(PaymentGrammarParser.After_beforeContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterDelivery_number_list(PaymentGrammarParser.Delivery_number_listContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitDelivery_number_list(PaymentGrammarParser.Delivery_number_listContext ctx) {
    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterDelivery_number_expr(PaymentGrammarParser.Delivery_number_exprContext ctx) {

    }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitDelivery_number_expr(PaymentGrammarParser.Delivery_number_exprContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitTerminal(TerminalNode node) { }
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation does nothing.</p>
     */
    @Override public void visitErrorNode(ErrorNode node) { }

}
