package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class ProductContributionToPurchaseExpenseRevenueAndProfitAddedEventEventListener {
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    public ProductContributionToPurchaseExpenseRevenueAndProfitAddedEventEventListener(ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
    }

    public void on (ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent event){
        YearMonth monthOfYear= YearMonth.now();
        LocalDate startDateOfCurrentMonth=monthOfYear.toLocalDate(1);
        ProductActualMetricsView productActualMetricsView = productActualMetricsViewRepository.findOne(new ProductVersionId(event.getProductId(),startDateOfCurrentMonth));
        productActualMetricsView.setPurchaseCost(productActualMetricsView.getPurchaseCost()+event.getPurchaseCostContribution());
        productActualMetricsView.setRevenue(productActualMetricsView.getRevenue()+event.getRevenueContribution());
        productActualMetricsView.setOperatingProfit(productActualMetricsView.getOperatingProfit()+ event.getProfitContribution());
        productActualMetricsViewRepository.save(productActualMetricsView);
    }
}
