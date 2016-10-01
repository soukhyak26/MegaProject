package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.services.Comparator.ProductAcualsViewReversedComparatorOnLocalDate;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mandar on 23-07-2016.
 */
public class PeriodBasedAggregator implements MetricsAggregator<ProductActualsView> {

    //aggregate daily historical data to weekly/monthly/quarterly data based on "period"  value
    public List<ProductActualsView> aggregate(List<ProductActualsView> historicalData, int period) {
        String productId = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        List<ProductActualsView> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??
        Collections.sort(historicalData, new ProductAcualsViewReversedComparatorOnLocalDate());
        if (period == 1) {
            return historicalData;
        }
        //outer for- creating chunks of daily data according to chunk period
        for (int periodIndex = 1; periodIndex <= historicalData.size() / period; periodIndex++) {
            ProductActualsView aggregatedView = null;
            //inner for-aggregating the chunks into single number
            for (int index = (period * periodIndex - 1); index >= period * (periodIndex - 1); index--) {
                ProductActualsView productActualsView = historicalData.get(index);
                if (null == productId) {
                    productId = productActualsView.getProductVersionId().getProductId();
                }
                startDate = productActualsView.getProductVersionId().getFromDate();
                endDate = productActualsView.getEndDate();
                if (null == aggregatedView) {
                    aggregatedView = new ProductActualsView(new ProductVersionId(productId, startDate), endDate.plusDays(period));
                }
                aggregatedView = productActualsView.visit(aggregatedView);
            }
            aggregateViewList.add(aggregatedView);
        }
        return aggregateViewList;
    }
}
