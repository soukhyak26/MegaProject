package com.affaince.subscription.date;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 20-08-2016.
 */
public class SysDateTest {

    @Test
    public void testSetCurrentDate() {
        SysDate.setCurrentDate(LocalDate.now().plusDays(10));
        assertThat(SysDate.now(), is(LocalDate.now().plusDays(10)));
        //resetting to current date
        SysDate.setCurrentDate(LocalDate.now().plusDays(10));
    }
}
