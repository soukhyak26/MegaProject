package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 23-07-2016.
 */
public class PeriodBasedAggregator implements MetricsAggregator<ProductForecastView> {

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<ProductForecastView> aggregate(List<ProductForecastView> historicalData, int period) {
        List<ProductForecastView> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        if (period == 1) {
            return historicalData;
        }
        //outer for- creating chunks of daily data according to chunk period
        for (int periodIndex = 1; periodIndex <= historicalData.size() / period; periodIndex++) {
            String productId = null;
            LocalDateTime startDate = null;
            LocalDateTime endDate = null;
            ProductForecastView firstView = historicalData.get(0);
            //create dummy forecastView and fill the details in for loop.
            ProductForecastView aggregatedView = new ProductForecastView(firstView.getProductVersionId(), firstView.getEndDate(), 0, 0, 0, ProductForecastStatus.ACTIVE);
            //inner for-aggregating the chunks into single number
            for (int index = period * (periodIndex - 1); index < period * periodIndex; index++) {
                ProductForecastView productForecastView = historicalData.get(index);
                startDate = productForecastView.getProductVersionId().getFromDate();
                endDate = productForecastView.getEndDate();
                aggregatedView.getProductVersionId().setFromDate(startDate);
                aggregatedView.setEndDate(endDate);
                aggregatedView.setNewSubscriptions(aggregatedView.getNewSubscriptions() + productForecastView.getNewSubscriptions());
                aggregatedView.setChurnedSubscriptions(aggregatedView.getChurnedSubscriptions() + productForecastView.getChurnedSubscriptions());
                aggregatedView.setTotalNumberOfExistingSubscriptions(productForecastView.getTotalNumberOfExistingSubscriptions());
            }
            aggregateViewList.add(aggregatedView);
        }
        return aggregateViewList;
    }
}
