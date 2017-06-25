package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;

/**
 * Created by rahul on 10/6/17.
 */
public class SysDateTimeChanger {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            SysDate.setCurrentDate(SysDate.now().plusDays(1));
            SysDateTime.setCurrentDateTime(SysDateTime.now().plusDays(1));
            Thread.sleep(3000);
        }
    }
}
