package com.affaince.subscription.business.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 13-01-2017.
 */
public class BusinessAccountCreatedEvent {
    private Integer id;
    private LocalDate dateOfProvision;
    public BusinessAccountCreatedEvent(Integer id,LocalDate dateOfProvision) {
        this.id=id;
        this.dateOfProvision=dateOfProvision;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDateOfProvision() {
        return dateOfProvision;
    }
}
