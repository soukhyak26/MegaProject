package com.affaince.subscription.date;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public class SysDateView {
    @Id
    /*@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)*/
    private LocalDate currentDate;

    public SysDateView(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }
}
