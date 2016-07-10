package com.affaince.subscription.product.services.aggregators;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandar on 10-07-2016.
 */
public class ActualsMonthlyAggregator implements MetricsAggregator {
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public void aggregate(String productId){
        List<ProductActualsView> productActualsViewsForTheTotalMonth = null;
        long newSubscriptions=0;
        long churnedSubscriptions=0;
        long existingSubscriptions=0;
        LocalDate monthStartDate=LocalDate.now().dayOfMonth().withMinimumValue();
        LocalDate monthEndDate = LocalDate.now().dayOfMonth().withMaximumValue();
        LocalDate currentDate = LocalDate.now();
        if (monthEndDate.isBefore(currentDate)) {
            productActualsViewsForTheTotalMonth=productActualsViewRepository.findByProductVersionId_ProductIdAndDateBetween(productId, monthStartDate , monthEndDate);
        } else {
            productActualsViewsForTheTotalMonth=productActualsViewRepository.findByProductVersionId_ProductIdAndDateBetween(productId, monthStartDate, currentDate);
        }
        for(ProductActualsView actuals: productActualsViewsForTheTotalMonth){
            newSubscriptions += actuals.getNewSubscriptions();
            churnedSubscriptions+= actuals.getChurnedSubscriptions();
            existingSubscriptions += actuals.getTotalNumberOfExistingSubscriptions();
        }
        ProductActualMetricsView monthlyView = new ProductActualMetricsView(new ProductVersionId(productId,monthStartDate),monthEndDate);
        monthlyView.setNewSubscriptions(newSubscriptions);
        monthlyView.setChurnedSubscriptions(churnedSubscriptions);
        monthlyView.setTotalNumberOfExistingSubscriptions(existingSubscriptions);
        ///CALCULATE Metrics ::: but HOw?
        productActualMetricsViewRepository.save(monthlyView);

    }
}
