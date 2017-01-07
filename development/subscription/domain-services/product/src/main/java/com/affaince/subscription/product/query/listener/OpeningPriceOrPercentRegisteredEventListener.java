package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.ProductActivateCommand;
import com.affaince.subscription.product.command.event.OpeningPriceOrPercentRegisteredEvent;
import com.affaince.subscription.product.query.exception.PriceInitializationNotAllowedException;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import com.affaince.subscription.product.web.exception.ProductReadinessException;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
@Component
public class OpeningPriceOrPercentRegisteredEventListener {
    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;
    private final ProductActivationStatusViewRepository productActivationStatusViewRepository;

    @Autowired
    public OpeningPriceOrPercentRegisteredEventListener(ProductConfigurationViewRepository productConfigurationViewRepository,
                                                        PriceBucketViewRepository priceBucketViewRepository,
                                                        ProductActivationStatusViewRepository productActivationStatusViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
    }

    @EventHandler
    public void on(OpeningPriceOrPercentRegisteredEvent event) throws PriceInitializationNotAllowedException, ProductReadinessException {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort);
        if (!activePriceBuckets.isEmpty()) {
            throw PriceInitializationNotAllowedException.build(event.getProductId());
        }


        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        if (ProductConfigurationValidator.getProductReadinessStatus(productActivationStatusView.getProductStatuses()).contains(
                ProductReadinessStatus.PRICEASSIGNABLE
        )) {
            PriceBucketView newPriceBucket = new PriceBucketView(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucket().getPriceBucketId()));
            newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getPriceBucket().getOfferedPriceOrPercentDiscountPerUnit());
            newPriceBucket.setTaggedPriceVersion(event.getPriceBucket().getTaggedPriceVersion());
            newPriceBucket.setEntityStatus(event.getPriceBucket().getEntityStatus());
            newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));

            priceBucketViewRepository.save(newPriceBucket);
            ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
            //set next forecast date as current date + configured duration for repeating forecast
            productConfigurationView.setNextForecastDate(SysDate.now().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast()));
            productConfigurationViewRepository.save(productConfigurationView);
            productActivationStatusView.addProductStatus(ProductStatus.PRODUCT_PRICE_ASSIGNED);
            productActivationStatusViewRepository.save(productActivationStatusView);
        } else {
            throw ProductReadinessException.build(event.getProductId(), ProductStatus.PRODUCT_PRICE_ASSIGNED);
        }

        if (ProductConfigurationValidator.getProductReadinessStatus(productActivationStatusView.getProductStatuses()).contains(
                ProductReadinessStatus.ACTIVABLE
        )) {
            final ProductActivateCommand command = new ProductActivateCommand(event.getProductId());
        }
    }
}
