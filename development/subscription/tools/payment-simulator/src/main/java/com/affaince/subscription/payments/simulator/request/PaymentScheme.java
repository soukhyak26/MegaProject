package com.affaince.subscription.payments.simulator.request;

import org.joda.time.LocalDate;

public class PaymentScheme {

    private String paymentSchemeName;
    private String paymentSchemeDescription;
    private String paymentSchemeRule;
    private LocalDate startDate;
    private LocalDate endDate;

    public String getPaymentSchemeRule() {
        return paymentSchemeRule;
    }

    public void setPaymentSchemeRule(String paymentSchemeRule) {
        this.paymentSchemeRule = paymentSchemeRule;
    }

    public String getPaymentSchemeName() {
        return paymentSchemeName;
    }

    public String getPaymentSchemeDescription() {
        return paymentSchemeDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "PaymentScheme{" +
                "paymentSchemeName='" + paymentSchemeName + '\'' +
                ", paymentSchemeDescription='" + paymentSchemeDescription + '\'' +
                ", paymentSchemeRule='" + paymentSchemeRule + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
