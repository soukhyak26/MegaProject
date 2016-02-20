package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.registration.command.event.ProductStatusReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.PriceBucketView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class ProductStatusUpdatedEventListener {

    private final ProductViewRepository productViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public ProductStatusUpdatedEventListener(ProductViewRepository productViewRepository, PriceBucketViewRepository priceBucketViewRepository) {
        this.productViewRepository = productViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @EventHandler
    public void on(ProductStatusReceivedEvent event) {
        ProductView productView = productViewRepository.findOne(event.getProductId());
        productView.setCurrentStockInUnits(event.getCurrentStockInUnits());
        Sort sort = new Sort(Sort.Direction.DESC, "fromDate");
        PriceBucketView currentLatestPriceBucket = priceBucketViewRepository.findOne(sort);
        currentLatestPriceBucket.setToDate(LocalDate.now());

        PriceBucketView priceBucketView = new PriceBucketView();
        priceBucketView.setFromDate(event.getCurrentPriceDate());
        priceBucketView.setMRP(event.getCurrentMRP());
        priceBucketView.setPurchasePricePerUnit(event.getCurrentPurchasePrice());
        priceBucketView.setEntityStatus(EntityStatus.ACTIVE);
        //productViewRepository.save(productView);
        priceBucketViewRepository.save(priceBucketView);
    }
}