package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/20/2017.
 */
public class PaymentSchemeCreatedEvent {
    private String schemeId;
    private String schemeName;
    private String schemeDescription;
    private String schemeEquationInJsonFormat;
    private String schemeRule;
    private LocalDate schemeStartDate;
    private LocalDate schemeEndDate;

    public PaymentSchemeCreatedEvent(String schemeId, String schemeName, String schemeDescription, String schemeEquationInJsonFormat, String schemeRule, LocalDate schemeStartDate, LocalDate schemeEndDate) {
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.schemeDescription = schemeDescription;
        this.schemeEquationInJsonFormat = schemeEquationInJsonFormat;
        this.schemeRule = schemeRule;
        this.schemeStartDate = schemeStartDate;
        this.schemeEndDate = schemeEndDate;
    }

    public PaymentSchemeCreatedEvent() {
    }

    public String getSchemeId() {
        return schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public String getSchemeDescription() {
        return schemeDescription;
    }

    public String getSchemeEquationInJsonFormat() {
        return schemeEquationInJsonFormat;
    }

    public String getSchemeRule() {
        return schemeRule;
    }

    public LocalDate getSchemeStartDate() {
        return schemeStartDate;
    }

    public LocalDate getSchemeEndDate() {
        return schemeEndDate;
    }
}
