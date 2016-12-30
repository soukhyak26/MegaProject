package com.affaince.subscription.common.Comparator;

import org.joda.time.LocalDate;

import java.util.Comparator;

/**
 * Created by rbsavaliya on 14-08-2016.
 */
public class LocalDateReversedComparator implements Comparator <LocalDate> {

    @Override
    public int compare(LocalDate o1, LocalDate o2) {
        return o2.compareTo(o1);
    }
}
