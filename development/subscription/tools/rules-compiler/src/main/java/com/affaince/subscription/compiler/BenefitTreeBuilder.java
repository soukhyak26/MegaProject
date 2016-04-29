package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;

public class BenefitTreeBuilder extends BenefitsRulesSetGrammarBaseListener {

    @Override public void exitSingle_rule(BenefitsRulesSetGrammarParser.Single_ruleContext ctx) {
        System.out.println(ctx.toString());
    }

    @Override public void exitCurrency_value(BenefitsRulesSetGrammarParser.Currency_valueContext ctx) {
        System.out.println(ctx.DECIMAL().getText());
    }
}
