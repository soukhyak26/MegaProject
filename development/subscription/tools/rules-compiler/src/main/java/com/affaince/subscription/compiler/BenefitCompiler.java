package com.affaince.subscription.compiler;

import com.affaince.subscription.BenefitsRulesSetGrammarLexer;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;
import com.affaince.subscription.pojos.RuleSet;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class BenefitCompiler {
    public RuleSet compile(String inputString) {
        ANTLRInputStream input = new ANTLRInputStream(inputString);
        BenefitsRulesSetGrammarLexer lexer = new BenefitsRulesSetGrammarLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        BenefitsRulesSetGrammarParser parser = new BenefitsRulesSetGrammarParser(tokens);

        BenefitTreeBuilder treeBuilder = new BenefitTreeBuilder();
        parser.addParseListener(treeBuilder);
        //parser.setErrorHandler(new ExceptionThrowingErrorHandler());

        parser.rule_set();

        return treeBuilder.getRuleSet();
    }
}
