package com.affaince.subscription.payments.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/20/2017.
 */
public class PaymentSchemeExpiredEvent {
    private String schemeId;
    private LocalDate expiryDate;
    public PaymentSchemeExpiredEvent(String schemeId, LocalDate expiryDate) {
        this.schemeId=schemeId;
        this.expiryDate=expiryDate;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
