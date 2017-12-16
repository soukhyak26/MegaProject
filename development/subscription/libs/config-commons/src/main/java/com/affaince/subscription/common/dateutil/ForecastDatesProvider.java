package com.affaince.subscription.common.dateutil;

import org.joda.time.LocalDateTime;
import org.joda.time.YearMonth;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 20-11-2016.
 */
@Component
public class ForecastDatesProvider {
    public LocalDateTime[] deriveStartAndEndDatesForAMonth(LocalDateTime startDate,int monthIndex) {
        LocalDateTime[] dates = new LocalDateTime[2];
        LocalDateTime newStartDate = startDate.plusMonths(monthIndex).withDayOfMonth(1);
        LocalDateTime newEndDate=newStartDate.monthOfYear().withMinimumValue();
        dates[0]=newStartDate;
        dates[1]=newEndDate;
        return dates;
    }
    public LocalDateTime[] deriveStartAndEndDates(LocalDateTime startDate, int chunkAggregationPeriod) {
        if (chunkAggregationPeriod == 30) {
            return getMonthStartEndDates(startDate);
        } else if (chunkAggregationPeriod == 90) {
            return getQuarterStartEndDates(startDate);
        } else if (chunkAggregationPeriod == 7) {
            return getWeekStartEndDates(startDate);
        } else {
            return getMonthStartEndDates(startDate);
        }
    }

    private LocalDateTime[] getMonthStartEndDates(LocalDateTime localDate) {
        LocalDateTime[] dates = new LocalDateTime[2];
        dates[0] = localDate.monthOfYear().withMinimumValue();
        dates[1] = localDate.monthOfYear().withMaximumValue();
        return dates;
    }

    private LocalDateTime[] getWeekStartEndDates(LocalDateTime localDate) {
        LocalDateTime[] dates = new LocalDateTime[2];
        dates[0] = localDate.dayOfWeek().withMinimumValue();
        dates[1] = localDate.dayOfWeek().withMaximumValue();
        return dates;
    }

    private LocalDateTime[] getQuarterStartEndDates(LocalDateTime localDate) {

        LocalDateTime[] dates = new LocalDateTime[2];

        // Get the month of the current date
        int i = localDate.getMonthOfYear();

        // 1st - 3rd months represent quarter 1
        // 4th - 6th months represent quarter 2
        // 7th - 9th months represent quarter 3
        // 10th - 12th months represent quarter 4

        int startI = 0;
        int endI = 0;

        if (i >= 1 && i <= 3) {
            startI = i - 1;
            endI = i - 3;
        } else if (i >= 4 && i <= 6) {
            startI = i - 4;
            endI = i - 6;
        } else if (i >= 7 && i <= 9) {
            startI = i - 7;
            endI = i - 9;
        } else if (i >= 10 && i <= 12) {
            startI = i - 10;
            endI = i - 12;
        }

        startI = Math.abs(startI);
        endI = Math.abs(endI);

        dates[0] = localDate.minusMonths(startI).dayOfMonth().withMinimumValue();
        dates[1] = localDate.plusMonths(endI).dayOfMonth().withMaximumValue();

        return dates;
    }

}
