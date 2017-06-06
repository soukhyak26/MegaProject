package com.affaince.subscription.date;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 20-08-2016.
 */
public class SysDateTest {

    @Test
    public void testSetCurrentDate() {
        SysDate.setCurrentDate(LocalDate.now().plusDays(1));
        assertThat(SysDate.now(), is(LocalDate.now().plusDays(1)));
        //resetting to current date
        //SysDate.setCurrentDate(LocalDate.now().plusDays(plusDays()));
    }

    @Test
    public void testSetCurrentDateTime() {
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("dd-MM-yyyy-hh:mm:ss");
        LocalDateTime currentDate = LocalDateTime.now().plusDays(1).plusHours(4);
        SysDateTime.setCurrentDateTime(currentDate);
        assertThat(SysDateTime.now(), is(LocalDateTime.parse(formatter.print(currentDate), formatter)));
        //SysDateTime.setCurrentDateTime(LocalDateTime.now().plusDays(plusDays()));
    }

    private int plusDays () {
        return 60;
    }
}
