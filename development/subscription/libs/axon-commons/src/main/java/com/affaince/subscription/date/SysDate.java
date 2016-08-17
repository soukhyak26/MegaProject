package com.affaince.subscription.date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public class SysDate {

    private static LocalDate localDate;
    private SysDate sysDate;

    public SysDate() {
        this.sysDate = new SysDate(LocalDate.now());
    }

    private SysDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public SysDate(String date, DateTimeFormatter dateTimeFormatter) {
        this.sysDate = new SysDate(LocalDate.parse(date, dateTimeFormatter));
    }

    public SysDate getSysDate() {
        return sysDate;
    }

    public static LocalDate now () {
        return localDate;
    }

    public static LocalDate minusDays (int days) {
        return localDate.minusDays(days);
    }

    public static LocalDate plusDays (int days) {
        return localDate.plusDays(days);
    }
}
