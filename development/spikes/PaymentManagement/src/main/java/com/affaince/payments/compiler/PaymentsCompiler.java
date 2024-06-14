package com.affaince.payments.compiler;

import com.affaince.payments.context.MetricsContext;
import com.affaince.payments.grammer.PaymentsLexer;
import com.affaince.payments.grammer.PaymentsParser;
import com.affaince.payments.processors.reg.PaymentSchemeListener;
import com.affaince.payments.scheme.PaymentsSchemeContext;
import com.affaince.payments.scheme.Scheme;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.List;
import java.util.Map;

public class PaymentsCompiler {
    public Scheme parseSchemeString(String schemeDefinitionString) {
        PaymentsLexer lexer = new PaymentsLexer(CharStreams.fromString(schemeDefinitionString));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PaymentsParser parser = new PaymentsParser(tokens);
        ParseTree tree = parser.scheme();
        ParseTreeWalker walker = new ParseTreeWalker();

        Scheme scheme = new Scheme();
        PaymentSchemeListener listener = new PaymentSchemeListener(scheme);
        walker.walk(listener, tree);
        scheme = listener.getScheme();  //plain parsed scheme without any inputs.. due for registration
        return scheme;
    }

    public Scheme execute(Scheme scheme){
        Scheme scheme1 = scheme.executeScheme();
       print(scheme1.getPaymentsSchemeContext());
        return scheme1;
    }

    public Scheme compile(String schemeString, MetricsContext metricsContext){
        Scheme scheme = parseSchemeString(schemeString);
        scheme.setMetricsContext(metricsContext);
        return scheme;
    }

    public static void print(PaymentsSchemeContext paymentsSchemeContext) {
        System.out.println("***********LETS GO THROUGH INPUT,COMPUTED AND OUTPUT VALUES**********");
        System.out.println("***********************INPUT VALUES**********************************");
        Map<String, Object> inputVariables = paymentsSchemeContext.getIntermediateVariables();
        for (Map.Entry<String, Object> inputEntry : inputVariables.entrySet()) {
            System.out.print(" Input field: " + inputEntry.getKey());
            System.out.println(" input value: " + inputEntry.getValue());
        }
        System.out.println("***********************END - INPUT VALUES**********************************");
        System.out.println("***********************OUTPUT VALUES**********************************");
        PaymentsSchemeContext.PaymentOutputContext outputContext = paymentsSchemeContext.getPaymentOutputContext();
        System.out.println("total benefit value : " + outputContext.getAdvancePaymentValue());
        System.out.println("is vesting BEFORE stated delivery number?: " + outputContext.isBefore());
        System.out.println("benefit points vesting distribution list");
        List<PaymentsSchemeContext.PaymentOutputContext.PaymentVestingDistribution> paymentVestingDistributionList = outputContext.getBenefitPaymentvestingDistributionList();
        for (PaymentsSchemeContext.PaymentOutputContext.PaymentVestingDistribution distribution : paymentVestingDistributionList) {
            System.out.print(" BEFORE/AFTER " + distribution.getDeliveryNumber() + " delivery,");
            System.out.println(" Vest " + distribution.getPaymentValueToBeVested() + " points");
        }
    }
}
