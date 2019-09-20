package com.verifier.domains.business.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

public class ProvisionCalendarVersionId implements Serializable {
    private String businessAccountId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public ProvisionCalendarVersionId(String businessAccountId, LocalDate startDate, LocalDate endDate) {
        this.businessAccountId = businessAccountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public ProvisionCalendarVersionId(){

    }

    public String getBusinessAccountId() {
        return businessAccountId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
