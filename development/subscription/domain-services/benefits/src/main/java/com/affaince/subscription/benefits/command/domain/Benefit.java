package com.affaince.subscription.benefits.command.domain;

import com.affaince.subscription.benefits.command.event.BenefitAddedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public class Benefit extends AbstractAnnotatedAggregateRoot<String> {
    @AggregateIdentifier
    private String benefitId;
    private String benefitEquation;
    private String benefitEquationInJsonFormat;
    private LocalDateTime activationStartTime;
    private LocalDateTime activationEndTime;

    public Benefit(String benefitId, String benefitEquation, String benefitEquationInJsonFormat, LocalDateTime activationStartTime, LocalDateTime activationEndTime) {
        apply(new BenefitAddedEvent(benefitId, benefitEquation, benefitEquationInJsonFormat, activationStartTime, activationEndTime));
    }

    public Benefit() {
    }

    @EventSourcingHandler
    public void on(BenefitAddedEvent event) {
        this.benefitId = event.getBenefitId();
        this.benefitEquation = benefitEquation;
        this.benefitEquationInJsonFormat = event.getBenefitEquationInJsonFormat();
        this.activationStartTime = event.getActivationStartTime();
        this.activationEndTime = event.getActivationEndTime();
    }
}
