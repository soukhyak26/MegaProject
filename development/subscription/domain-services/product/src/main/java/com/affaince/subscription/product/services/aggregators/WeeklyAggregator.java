package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
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
public class WeeklyAggregator<T extends ProductSubscriptionMetricsView> implements MetricsAggregator<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodBasedAggregator.class);
    private Class<T> typeClass;

    public WeeklyAggregator(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public Class<T> getTypeClass() {
        return typeClass;
    }

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<T> aggregate(List<T> historicalData, int period) {
        List<T> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        Map<String, T> weekwiseAggregatesMap = new TreeMap<>();
        for (T singleRecord : historicalData) {
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            T weekwiseAggregateView = null;

            int currentYear = singleRecord.getProductVersionId().getFromDate().getYear();
            int currentMonth = singleRecord.getProductVersionId().getFromDate().getMonthOfYear();
            int currentWeek = singleRecord.getProductVersionId().getFromDate().getWeekOfWeekyear();
            String key = currentWeek + "_" + currentMonth + "_" + currentYear;

            if (weekwiseAggregatesMap.containsKey(key)) {
                weekwiseAggregateView = weekwiseAggregatesMap.get(key);
            } else {
                try {
                    weekwiseAggregateView = (T) this.getTypeClass()
                            .getDeclaredConstructor(ProductVersionId.class, LocalDate.class, Long.TYPE, Long.TYPE, Long.TYPE,LocalDate.class).
                                    newInstance(singleRecord.getProductVersionId(), singleRecord.getEndDate(), 0, 0, 0,singleRecord.getForecastDate());
                } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException ex) {
                    LOGGER.error("Cannot construct object of generic type using reflection: " + ex.getMessage());
                }
            }
            startDate = singleRecord.getProductVersionId().getFromDate();
            endDate = singleRecord.getEndDate();
            weekwiseAggregateView.getProductVersionId().setFromDate(startDate);
            weekwiseAggregateView.setEndDate(endDate);
            weekwiseAggregateView.setNewSubscriptions(weekwiseAggregateView.getNewSubscriptions() + singleRecord.getNewSubscriptions());
            weekwiseAggregateView.setChurnedSubscriptions(weekwiseAggregateView.getChurnedSubscriptions() + singleRecord.getChurnedSubscriptions());
            weekwiseAggregateView.setTotalNumberOfExistingSubscriptions(singleRecord.getTotalNumberOfExistingSubscriptions());
            weekwiseAggregatesMap.put(key, weekwiseAggregateView);
        }
        return new ArrayList(weekwiseAggregatesMap.values());
    }
}