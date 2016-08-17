package com.affaince.subscription.date;

import com.affaince.subscription.Application;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class SysDateTest {

    @Autowired
    private SysDate sysDate;

    @Test
    public void testSetCurrentDate() {
        sysDate.setCurrentDate(LocalDate.now().plusDays(10));
        assertThat(sysDate.now(), is(LocalDate.now().plusDays(10)));
    }
}
