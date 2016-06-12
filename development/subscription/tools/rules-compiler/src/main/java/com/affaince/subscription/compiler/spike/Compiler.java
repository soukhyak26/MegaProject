package com.affaince.subscription.compiler.spike;

import com.affaince.subscription.RuleSetGrammarLexer;
import com.affaince.subscription.RuleSetGrammarParser;
import com.affaince.subscription.compiler.ExceptionThrowingErrorHandler;
import com.affaince.subscription.pojos.RuleSet;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

public class Compiler {
    public RuleSet compile(String inputString) {
        ANTLRInputStream input = new ANTLRInputStream(inputString);
        RuleSetGrammarLexer lexer = new RuleSetGrammarLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        RuleSetGrammarParser parser = new RuleSetGrammarParser(tokens);

        TreeBuilder treeBuilder = new TreeBuilder();
        parser.addParseListener(treeBuilder);
        parser.setErrorHandler(new ExceptionThrowingErrorHandler());

        parser.rule_set();

        return treeBuilder.getRuleSet();
    }
}
