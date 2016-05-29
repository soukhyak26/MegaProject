package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.registration.command.event.ProductStatusUpdatedEvent;
import com.affaince.subscription.product.registration.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductsMonthlyStatisticsViewRepository;
import com.affaince.subscription.product.registration.query.view.PriceBucketView;
import com.affaince.subscription.product.registration.query.view.ProductMonthlyStatisticsView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.vo.PriceTaggedWithProduct;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class ProductStatusUpdatedEventListener {

    private final ProductViewRepository productViewRepository;
    private final ProductsMonthlyStatisticsViewRepository productsMonthlyStatisticsViewRepository;

    @Autowired
    public ProductStatusUpdatedEventListener(ProductViewRepository productViewRepository, ProductsMonthlyStatisticsViewRepository productsMonthlyStatisticsViewRepository) {
        this.productViewRepository = productViewRepository;
        this.productsMonthlyStatisticsViewRepository = productsMonthlyStatisticsViewRepository;
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        ProductView productView = productViewRepository.findOne(event.getProductId());
        productView.setCurrentStockInUnits(event.getCurrentStockInUnits());
        Sort sort = new Sort(Sort.Direction.DESC, "productMonthlyVersionId.fromDate");

        //if purchase price in incoming event and that of latest price bucket are same do nothing,as there is no change;Else create new price bucket and set new purchase price/MRP in it.
        ProductMonthlyStatisticsView latestStatisticsView = productsMonthlyStatisticsViewRepository.findByProductMonthlyVersionId_ProductId(event.getProductId(),sort).get(0);

        if (latestStatisticsView.getTaggedPriceVersions().first().getPurchasePricePerUnit() != event.getCurrentPurchasePrice()) {
            PriceTaggedWithProduct newTaggedPrice = new PriceTaggedWithProduct(event.getCurrentPurchasePrice(),event.getCurrentMRP(),event.getCurrentPriceDate());
            latestStatisticsView.getTaggedPriceVersions().add(newTaggedPrice);
            productsMonthlyStatisticsViewRepository.save(latestStatisticsView);
        }
    }
}
