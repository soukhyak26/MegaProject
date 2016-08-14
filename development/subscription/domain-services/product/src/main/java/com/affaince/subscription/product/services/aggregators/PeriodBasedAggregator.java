package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;

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
        LocalDate startDate = null;
        LocalDate endDate = null;

        List<ProductActualsView> aggregateViewList = new ArrayList<>();
        //are product actuals view sorted??

        Collections.sort(historicalData);
        for (int periodIndex = 0; periodIndex < historicalData.size() % period; periodIndex++) {
            ProductActualsView aggregatedView = null;
            for (int index = period; index <= 0; index--) {
                ProductActualsView productActualsView = historicalData.get(index);
                if (null == productId) {
                    productId = productActualsView.getProductVersionId().getProductId();
                }
                if (null == startDate) {
                    startDate = productActualsView.getProductVersionId().getFromDate();
                }
                if (index == 0) {
                    endDate = productActualsView.getEndDate();
                }
                if (null == aggregatedView) {
                    aggregatedView = new ProductActualsView(new ProductVersionId(productId, startDate), endDate);
                }
                aggregatedView = productActualsView.visit(aggregatedView);
            }
            aggregateViewList.add(aggregatedView);
            startDate = null;
            endDate = null;
        }
        return aggregateViewList;
    }
}
