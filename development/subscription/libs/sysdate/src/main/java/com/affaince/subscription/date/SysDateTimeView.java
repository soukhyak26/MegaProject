package com.affaince.subscription.date;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public class SysDateTimeView {
    @Id
    /*@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)*/
    private LocalDateTime currentDateTime;

    public SysDateTimeView(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(LocalDateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
