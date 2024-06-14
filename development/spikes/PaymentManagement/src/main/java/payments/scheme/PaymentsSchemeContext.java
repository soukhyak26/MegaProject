package payments.scheme;

import com.affaince.payments.context.MetricsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentsSchemeContext {

    private MetricsContext metricsContext;
    private PaymentOutputContext paymentOutputContext;

    private Map<String,Object> intermediateVariables;

    public PaymentsSchemeContext(){
        paymentOutputContext =new PaymentOutputContext();
        intermediateVariables = new HashMap<>();
    }

    public MetricsContext getMetricsContext() {
        return metricsContext;
    }

    public void setMetricsContext(MetricsContext metricsContext) {
        this.metricsContext = metricsContext;
    }

    public void addToInputVariables(String variableName, Object value){
        intermediateVariables.put(variableName,value);
    }
    public void addToComputedVariables(String variableName,Object value) {
        intermediateVariables.put(variableName,value);
    }

    public void addToPaymentVestingDistributionList(double deliveryNumber, double paymentValueToBeVested){
        this.paymentOutputContext.addToPaymentVestingDistributionList(deliveryNumber,paymentValueToBeVested);
    }
    public void setBefore(boolean isBefore){
        this.paymentOutputContext.setBefore(isBefore);
    }

    public Map<String, Object> getIntermediateVariables() {
        return intermediateVariables;
    }

    public PaymentOutputContext getPaymentOutputContext() {
        return paymentOutputContext;
    }
    public void setAdvancePaymentValue(Double paymentValue){
        paymentOutputContext.setAdvancePaymentValue(paymentValue);
    }
    public void setEventOfAdvancePayment(String eventOfAdvancePayment) {
        paymentOutputContext.eventOfAdvancePayment = eventOfAdvancePayment;
    }
    public Object searchVariableValue(String variableName){
        Map.Entry<String,Object> variableEntry =  intermediateVariables.entrySet().stream().filter(var->var.getKey().equals(variableName)).findFirst().orElse(null);
        if(null != variableEntry) {
            return variableEntry.getValue();
        }
        return null;
    }
    public class PaymentOutputContext {
        private double advancePaymentValue;
        private String eventOfAdvancePayment;
        private boolean isBefore;
        private List<PaymentVestingDistribution> paymentVestingDistributionList;

        PaymentOutputContext(){
            paymentVestingDistributionList = new ArrayList<>();
        }

        public boolean isBefore() {
            return isBefore;
        }

        public void setBefore(boolean before) {
            isBefore = before;
        }

        public double getAdvancePaymentValue() {
            return advancePaymentValue;
        }

        public void setAdvancePaymentValue(double advancePaymentValue) {
            this.advancePaymentValue = advancePaymentValue;
        }

        public List<PaymentVestingDistribution> getBenefitPaymentvestingDistributionList() {
            return paymentVestingDistributionList;
        }

        public void setBenefitVestingDistributionList(List<PaymentVestingDistribution> paymentVestingDistributionList) {
            this.paymentVestingDistributionList = paymentVestingDistributionList;
        }

        public String getEventOfAdvancePayment() {
            return eventOfAdvancePayment;
        }

        public void setEventOfAdvancePayment(String eventOfAdvancePayment) {
            this.eventOfAdvancePayment = eventOfAdvancePayment;
        }

        public void addToPaymentVestingDistributionList(double deliveryNumber, double paymentValueToBeVested){
            PaymentVestingDistribution paymentVestingDistribution = new PaymentVestingDistribution(deliveryNumber,paymentValueToBeVested);
            this.paymentVestingDistributionList.add(paymentVestingDistribution);
        }
        public class PaymentVestingDistribution {
            private double deliveryNumber;
            private double paymentValueToBeVested;

            public PaymentVestingDistribution(double deliveryNumber, double paymentValueToBeVested) {
                this.deliveryNumber = deliveryNumber;
                this.paymentValueToBeVested = paymentValueToBeVested;
            }

            public double getDeliveryNumber() {
                return deliveryNumber;
            }

            public double getPaymentValueToBeVested() {
                return paymentValueToBeVested;
            }
        }
    }
}
