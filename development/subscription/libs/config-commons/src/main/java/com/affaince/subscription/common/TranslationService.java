package com.affaince.subscription.common;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rsavaliya on 17/1/16.
 */
public class TranslationService {

    public static Integer[] getWeeksOfMonth(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        Set<Integer> weeks = new HashSet<Integer>();
        int ndays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < ndays; i++) {
            weeks.add(cal.get(Calendar.WEEK_OF_YEAR));
            cal.add(Calendar.DATE, 1);
        }

        return weeks.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        Integer[] weeksOfMonth = TranslationService.getWeeksOfMonth(10, 2015);
        for (int week : weeksOfMonth) {
            System.out.println("###Week: " + week);
        }

    }
}
