package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarBaseListener;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;

public class BenefitTreeBuilder extends BenefitsRulesSetGrammarBaseListener {

    @Override public void exitConvert_expr(BenefitsRulesSetGrammarParser.Convert_exprContext ctx) {
        System.out.println(ctx.money_convert_expr().getChildCount());
        for (int i=1; i<ctx.money_convert_expr().getChildCount();i++) {
            System.out.println( (ctx.money_convert_expr().getChild(i).getText()));
        }
    }
}
