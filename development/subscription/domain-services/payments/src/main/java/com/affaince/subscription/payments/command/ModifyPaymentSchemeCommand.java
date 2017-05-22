package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/20/2017.
 */
public class ModifyPaymentSchemeCommand {
    private String paymentSchemeName;
    private String paymentSchemeDescription;
    private String paymentSchemeRule;
    private LocalDate startDate;
    private LocalDate endDate;

    public ModifyPaymentSchemeCommand(String paymentSchemeName, String paymentSchemeDescription, String paymentSchemeRule, LocalDate startDate, LocalDate endDate) {
        this.paymentSchemeName = paymentSchemeName;
        this.paymentSchemeDescription = paymentSchemeDescription;
        this.paymentSchemeRule = paymentSchemeRule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

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

}
