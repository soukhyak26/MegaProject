package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.command.event.ProductStatusUpdatedEvent;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class ProductStatusUpdatedEventListener {

    private final ProductViewRepository productViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    public ProductStatusUpdatedEventListener(ProductViewRepository productViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productViewRepository = productViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        ProductView productView = productViewRepository.findOne(event.getProductId());
        productView.setCurrentStockInUnits(event.getCurrentStockInUnits());
        Sort sort = new Sort(Sort.Direction.DESC, "productMonthlyVersionId.fromDate");

        //if purchase price in incoming event and that of latest price bucket are same do nothing,as there is no change;Else create new price bucket and set new purchase price/MRP in it.
        ProductActualMetricsView latestStatisticsView = productActualMetricsViewRepository.findByProductMonthlyVersionId_ProductId(event.getProductId(),sort).get(0);

        if (latestStatisticsView.getTaggedPriceVersions().first().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceTaggedWithProduct newTaggedPrice = new PriceTaggedWithProduct(event.getCurrentPurchasePrice(),event.getCurrentMRP(),event.getCurrentPriceDate());
            latestStatisticsView.getTaggedPriceVersions().add(newTaggedPrice);
            productActualMetricsViewRepository.save(latestStatisticsView);
        }
    }
}