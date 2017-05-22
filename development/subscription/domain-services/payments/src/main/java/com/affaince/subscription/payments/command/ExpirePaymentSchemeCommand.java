package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/20/2017.
 */
public class ExpirePaymentSchemeCommand {
    private String schemeId;
    private LocalDate expiryDate;

    public ExpirePaymentSchemeCommand(String schemeId, LocalDate expiryDate) {
        this.schemeId = schemeId;
        this.expiryDate = expiryDate;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
