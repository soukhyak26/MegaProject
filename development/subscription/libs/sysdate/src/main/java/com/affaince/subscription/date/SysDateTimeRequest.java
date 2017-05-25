package com.affaince.subscription.date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by rahul on 25/5/17.
 */
public class SysDateTimeRequest {

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate sysDate;
    @JsonSerialize (using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime sysDateTime;

    public LocalDate getSysDate() {
        return sysDate;
    }

    public void setSysDate(LocalDate sysDate) {
        this.sysDate = sysDate;
    }

    public LocalDateTime getSysDateTime() {
        return sysDateTime;
    }

    public void setSysDateTime(LocalDateTime sysDateTime) {
        this.sysDateTime = sysDateTime;
    }
}
