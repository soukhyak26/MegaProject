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
public class MonthlyAggregator implements MetricsAggregator<Aggregatable>{
    private static final Logger LOGGER = LoggerFactory.getLogger(MonthlyAggregator.class);
    private Class<Aggregatable> typeClass;

    public MonthlyAggregator(Class<Aggregatable> typeClass){
        this.typeClass=typeClass;
    }

    public Class<Aggregatable> getTypeClass() {
        return typeClass;
    }

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<Aggregatable> aggregate(List<Aggregatable> historicalData, int period) {
        List<Aggregatable> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        Map<String, Aggregatable> monthwiseAggregatesMap = new TreeMap<>();
        for (Aggregatable singleRecord : historicalData) {
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            Aggregatable monthwiseAggregate = null;

            int currentYear = singleRecord.getStartDate().getYear();
            int currentMonth = singleRecord.getStartDate().getMonthOfYear();
            String key = currentMonth + "_" + currentYear;

            if (monthwiseAggregatesMap.containsKey(key)) {
                monthwiseAggregate = monthwiseAggregatesMap.get(key);
            } else {
                try {
                    monthwiseAggregate= this.getTypeClass().newInstance().buildAggregatable(singleRecord);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            endDate = singleRecord.getEndDate();
            monthwiseAggregate.setEndDate(endDate);
            if(singleRecord.getAggregationType()== AggregationType.DAILY_NEW) {
                monthwiseAggregate.setAggregateValue(monthwiseAggregate.getAggregateValue() + singleRecord.getAggregateValue());
            }else{
                monthwiseAggregate.setAggregateValue(singleRecord.getAggregateValue());
            }
            monthwiseAggregatesMap.put(key,monthwiseAggregate);
        }
        return new ArrayList(monthwiseAggregatesMap.values());
    }
}