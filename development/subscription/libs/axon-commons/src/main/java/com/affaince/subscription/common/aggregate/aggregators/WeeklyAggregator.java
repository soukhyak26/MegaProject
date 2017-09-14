package com.affaince.subscription.common.aggregate.aggregators;

import com.affaince.subscription.common.vo.AggregationType;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mandar on 4/17/2017.
 */
public class WeeklyAggregator<T extends Aggregatable> implements MetricsAggregator<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyAggregator.class);
    private Class<T> typeClass;

    public WeeklyAggregator(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public Class<T> getTypeClass() {
        return typeClass;
    }

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<T> aggregate(List<T> historicalData, int period) {
        List<Aggregatable> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        Map<String, Aggregatable> weekwiseAggregatesMap = new TreeMap<>();
        for (Aggregatable singleRecord : historicalData) {
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            Aggregatable weekwiseAggregate = null;

            int currentYear = singleRecord.getStartDate().getYear();
            int currentMonth = singleRecord.getStartDate().getMonthOfYear();
            int currentWeek = singleRecord.getStartDate().getWeekOfWeekyear();
            String key = currentWeek + "_" + currentMonth + "_" + currentYear;

            if (weekwiseAggregatesMap.containsKey(key)) {
                weekwiseAggregate = weekwiseAggregatesMap.get(key);
            } else {
                try {
                    weekwiseAggregate = this.getTypeClass().newInstance().buildAggregatable(singleRecord);
                } catch (InstantiationException |  IllegalAccessException ex) {
                    LOGGER.error("Cannot construct object of generic type using reflection: " + ex.getMessage());
                }
            }
            startDate = singleRecord.getStartDate();
            endDate = singleRecord.getEndDate();
            weekwiseAggregate.setStartDate(startDate);
            weekwiseAggregate.setEndDate(endDate);
            if(singleRecord.getAggregationType()== AggregationType.DAILY_NEW) {
                weekwiseAggregate.setAggregateValue(weekwiseAggregate.getAggregateValue() + singleRecord.getAggregateValue());
            }else{
                weekwiseAggregate.setAggregateValue(singleRecord.getAggregateValue());
            }

            weekwiseAggregatesMap.put(key, weekwiseAggregate);
        }
        return new ArrayList(weekwiseAggregatesMap.values());
    }
}