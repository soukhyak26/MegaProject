package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 23-07-2016.
 */
public class PeriodBasedAggregator<T extends ProductSubscriptionMetricsView> implements MetricsAggregator<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodBasedAggregator.class);
    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<T> aggregate(List<T> historicalData, int period) {
        List<T> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        //outer for- creating chunks of daily data according to chunk period
        for (int periodIndex = 1; periodIndex <= historicalData.size() / period; periodIndex++) {
            String productId = null;
            LocalDate startDate = null;
            LocalDate endDate = null;
            T firstView = historicalData.get(0);
            //create dummy forecastView/actualsView and fill the details in for loop.
            try {
                T aggregatedView = (T) this.getClass()
                        .getTypeParameters()[0]
                        .getClass()
                        .getDeclaredConstructor(ProductVersionId.class, LocalDate.class, Long.class, Long.class, Long.class,LocalDate.class)
                        .newInstance(firstView.getProductVersionId(), firstView.getEndDate(), 0, 0, 0,firstView.getForecastDate());
                //inner for-aggregating the chunks into single number
                for (int index = period * (periodIndex - 1); index < period * periodIndex; index++) {
                    T productForecastView = historicalData.get(index);
                    startDate = productForecastView.getProductVersionId().getFromDate();
                    endDate = productForecastView.getEndDate();
                    aggregatedView.getProductVersionId().setFromDate(startDate);
                    aggregatedView.setEndDate(endDate);
                    aggregatedView.setNewSubscriptions(aggregatedView.getNewSubscriptions() + productForecastView.getNewSubscriptions());
                    aggregatedView.setChurnedSubscriptions(aggregatedView.getChurnedSubscriptions() + productForecastView.getChurnedSubscriptions());
                    aggregatedView.setTotalNumberOfExistingSubscriptions(productForecastView.getTotalNumberOfExistingSubscriptions());
                }
                aggregateViewList.add(aggregatedView);
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException ex) {
                LOGGER.error("Cannot construct object of generic type using reflection: " + ex.getMessage());
            }
        }
        return aggregateViewList;
    }
}
