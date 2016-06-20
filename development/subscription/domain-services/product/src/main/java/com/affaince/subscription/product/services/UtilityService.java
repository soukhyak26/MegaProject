package com.affaince.subscription.product.services;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class UtilityService {

    public static String getFromToDateString(LocalDate fromDate, LocalDate toDate) {
        return fromDate.toString() + "_" + toDate.toString();
    }
}
