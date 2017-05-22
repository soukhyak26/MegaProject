package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.payments.command.event.PaymentSchemeCreatedEvent;
import com.affaince.subscription.payments.command.event.PaymentSchemeExpiredEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/19/2017.
 */
public class PaymentScheme extends AbstractAnnotatedAggregateRoot<String>{
    @AggregateIdentifier
    private String schemeId;
    private String schemeName;
    private String schemeDescription;
    private String schemeRuleInJsonFormat;
    private String schemeRule;
    private LocalDate schemeStartDate;
    private LocalDate schemeEndDate;

    public PaymentScheme(String schemeId, String schemeName, String schemeDescription, String schemeRuleInJsonFormat, String schemeRule, LocalDate schemeStartDate, LocalDate schemeEndDate) {
        apply(new PaymentSchemeCreatedEvent(schemeId,schemeName,schemeDescription, schemeRuleInJsonFormat,schemeRule,schemeStartDate,schemeEndDate));
    }

    @EventSourcingHandler
    public void on(PaymentSchemeCreatedEvent event){
        this.schemeId = schemeId;
        this.schemeName = schemeName;
        this.schemeDescription = schemeDescription;
        this.schemeRuleInJsonFormat = schemeRuleInJsonFormat;
        this.schemeRule = schemeRule;
        this.schemeStartDate = schemeStartDate;
        this.schemeEndDate = schemeEndDate;
    }

    public void expireScheme(LocalDate expiryDate) {
        apply(new PaymentSchemeExpiredEvent(this.schemeId,expiryDate));
    }

    @EventSourcingHandler
    public  void on(PaymentSchemeExpiredEvent event){
        this.schemeEndDate=event.getExpiryDate();
    }

}
