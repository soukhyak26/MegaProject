package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by mandar on 4/17/2017.
 */
public class MonthlyAggregator<T extends ProductSubscriptionMetricsView> implements MetricsAggregator<T>{
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodBasedAggregator.class);

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<T> aggregate(List<T> historicalData, int period) {
        List<T> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        Map<String, T> monthwiseAggregatesMap = new TreeMap<>();
        for (T singleRecord : historicalData) {
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            T monthwiseAggregateView = null;

            int currentYear = singleRecord.getProductVersionId().getFromDate().getYear();
            int currentMonth = singleRecord.getProductVersionId().getFromDate().getMonthOfYear();
            String key = currentMonth + "_" + currentYear;

            if (monthwiseAggregatesMap.containsKey(key)) {
                monthwiseAggregateView = monthwiseAggregatesMap.get(key);
            } else {
                try {
                    monthwiseAggregateView = (T) this.getClass()
                            .getTypeParameters()[0]
                            .getClass()
                            .getDeclaredConstructor(ProductVersionId.class, LocalDateTime.class, Long.class, Long.class, Long.class)
                            .newInstance(singleRecord.getProductVersionId(), singleRecord.getEndDate(), 0, 0, 0);
                } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException ex) {
                    LOGGER.error("Cannot construct object of generic type using reflection: " + ex.getMessage());
                }
            }
            startDate = singleRecord.getProductVersionId().getFromDate();
            endDate = singleRecord.getEndDate();
            monthwiseAggregateView.getProductVersionId().setFromDate(startDate);
            monthwiseAggregateView.setEndDate(endDate);
            monthwiseAggregateView.setNewSubscriptions(monthwiseAggregateView.getNewSubscriptions() + singleRecord.getNewSubscriptions());
            monthwiseAggregateView.setChurnedSubscriptions(monthwiseAggregateView.getChurnedSubscriptions() + singleRecord.getChurnedSubscriptions());
            monthwiseAggregateView.setTotalNumberOfExistingSubscriptions(singleRecord.getTotalNumberOfExistingSubscriptions());
            monthwiseAggregatesMap.put(key,monthwiseAggregateView);
        }
        return new ArrayList(monthwiseAggregatesMap.values());
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();

    }
}