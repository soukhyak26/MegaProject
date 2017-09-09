package com.affaince.subscription.compiler.paymentscheme;

import com.affaince.subscription.BenefitsRulesSetGrammarLexer;
import com.affaince.subscription.BenefitsRulesSetGrammarParser;
import com.affaince.subscription.PaymentGrammarLexer;
import com.affaince.subscription.PaymentGrammarParser;
import com.affaince.subscription.compiler.*;
import com.affaince.subscription.pojos.PaymentExpression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

/**
 * Created by mandar on 5/20/2017.
 */
public class PaymentSchemeCompiler {
    public PaymentExpression compile(String inputString) {
        ANTLRInputStream input = new ANTLRInputStream(inputString);
        PaymentGrammarLexer lexer = new PaymentGrammarLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);
        PaymentGrammarParser parser = new PaymentGrammarParser(tokens);

        PaymentRuleParser treeBuilder = new PaymentRuleParser();
        parser.addParseListener(treeBuilder);
        //parser.setErrorHandler(new ExceptionThrowingErrorHandler());

        parser.payment_rule_set();

        return treeBuilder.getPaymentExpression();
    }
}
