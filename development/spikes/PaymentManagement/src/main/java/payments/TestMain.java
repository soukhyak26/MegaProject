package payments;

import com.affaince.payments.compiler.PaymentsCompiler;
import com.affaince.payments.context.MetricsContext;
import com.affaince.payments.scheme.PaymentsSchemeContext;
import com.affaince.payments.scheme.Scheme;

import java.util.List;


public class TestMain {
    public static void main(String[] args) {
        System.out.println("IN MAIN");
        execute();
    }

    public static void execute() {

        String paymentSchemeStr =
                "given " +
                    "CURRENT_SUBSCRIPTION_AMOUNT as input;" +
                    "TOTAL_DELIVERIES as input;" +
                    "ADVANCE_PAYMENT_SHARE=0.5;" +
                "pay ADVANCE_PAYMENT=ADVANCE_PAYMENT_SHARE * CURRENT_SUBSCRIPTION_AMOUNT on subscription confirmation;" +
                "then " +
                "pay RESIDUAL_DUE_AMOUNT=CURRENT_SUBSCRIPTION_AMOUNT-ADVANCE_PAYMENT " +
                        "after 1/2 of TOTAL_DELIVERIES, 3/4 of TOTAL_DELIVERIES " +
                    "in default proportion;";

        MetricsContext context= new MetricsContext();
        context.addToAdvancePayMetrics("CURRENT_SUBSCRIPTION_AMOUNT",100000);
        context.addToAdvancePayMetrics("TOTAL_DELIVERIES",10);

        //Build scheme object form the scheme string
        PaymentsCompiler compiler = new PaymentsCompiler();
        //all the units are supplied with input values
        System.out.println("*****************");
        System.out.println(paymentSchemeStr);
        System.out.println("*****************");
        Scheme scheme = compiler.compile(paymentSchemeStr,context);
        compiler.execute(scheme);
        PaymentsSchemeContext.PaymentOutputContext paymentOutputContext = scheme.getPaymentsSchemeContext().getPaymentOutputContext() ;
        System.out.println("Advance Payment : " + paymentOutputContext.getAdvancePaymentValue());
        System.out.println("Event Of AdvancePayment : " + paymentOutputContext.getEventOfAdvancePayment());
        List<PaymentsSchemeContext.PaymentOutputContext.PaymentVestingDistribution> vestingDistributionList = paymentOutputContext.getBenefitPaymentvestingDistributionList();
        for(PaymentsSchemeContext.PaymentOutputContext.PaymentVestingDistribution vestingDistribution : vestingDistributionList){
            System.out.println("delivery Number : " + vestingDistribution.getDeliveryNumber());
            System.out.println("Payment To Be Vested: " + vestingDistribution.getPaymentValueToBeVested());
        }
    }
}
