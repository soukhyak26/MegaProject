package com.verifier.domains.payments.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/20/2017.
 */
@Document(collection="PaymentSchemeView")
public class PaymentSchemesView {
    @Id
    private String schemeId;
    private String schemeName;
    private String schemeDescription;
    private String schemeRuleInJsonFormat;
    private String schemeRule;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate schemeStartDate;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate schemeEndDate;

    public PaymentSchemesView() {
    }

    public PaymentSchemesView(String schemeId, String schemeName, String schemeDescription, String schemeRuleInJsonFormat, String schemeRule, LocalDate schemeStartDate, LocalDate schemeEndDate) {
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.schemeDescription = schemeDescription;
        this.schemeRuleInJsonFormat = schemeRuleInJsonFormat;
        this.schemeRule = schemeRule;
        this.schemeStartDate = schemeStartDate;
        this.schemeEndDate = schemeEndDate;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getSchemeDescription() {
        return schemeDescription;
    }

    public void setSchemeDescription(String schemeDescription) {
        this.schemeDescription = schemeDescription;
    }

    public String getSchemeRuleInJsonFormat() {
        return schemeRuleInJsonFormat;
    }

    public void setSchemeRuleInJsonFormat(String schemeRuleInJsonFormat) {
        this.schemeRuleInJsonFormat = schemeRuleInJsonFormat;
    }

    public String getSchemeRule() {
        return schemeRule;
    }

    public void setSchemeRule(String schemeRule) {
        this.schemeRule = schemeRule;
    }

    public LocalDate getSchemeStartDate() {
        return schemeStartDate;
    }

    public void setSchemeStartDate(LocalDate schemeStartDate) {
        this.schemeStartDate = schemeStartDate;
    }

    public LocalDate getSchemeEndDate() {
        return schemeEndDate;
    }

    public void setSchemeEndDate(LocalDate schemeEndDate) {
        this.schemeEndDate = schemeEndDate;
    }
}
