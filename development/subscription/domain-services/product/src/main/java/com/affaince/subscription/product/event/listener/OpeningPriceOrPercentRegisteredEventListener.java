package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.command.ProductActivateCommand;
import com.affaince.subscription.product.event.OpeningPriceOrPercentRegisteredEvent;
import com.affaince.subscription.product.event.exception.PriceInitializationNotAllowedException;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActivationStatusViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.validator.ProductConfigurationValidator;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
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
    private final SubscriptionCommandGateway subscriptionCommandGateway;

    @Autowired
    public OpeningPriceOrPercentRegisteredEventListener(ProductConfigurationViewRepository productConfigurationViewRepository,
                                                        PriceBucketViewRepository priceBucketViewRepository,
                                                        ProductActivationStatusViewRepository productActivationStatusViewRepository,
                                                        SubscriptionCommandGateway subscriptionCommandGateway) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
        this.productActivationStatusViewRepository = productActivationStatusViewRepository;
        this.subscriptionCommandGateway = subscriptionCommandGateway;
    }

    @EventHandler
    public void on(OpeningPriceOrPercentRegisteredEvent event) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort);
        if (!activePriceBuckets.isEmpty()) {
            throw PriceInitializationNotAllowedException.build(event.getProductId());
        }


        final ProductActivationStatusView productActivationStatusView = productActivationStatusViewRepository.findByProductId(event.getProductId());
        if (ProductConfigurationValidator.getProductReadinessStatus(productActivationStatusView.getProductStatuses()).contains(
                ProductReadinessStatus.PRICEASSIGNABLE
        )) {
            PriceBucketView newPriceBucket = new PriceBucketView(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucketId()),event.getProductPricingCategory());
            newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getOfferedPriceOrPercentDiscountPerUnit());
            newPriceBucket.setTaggedPriceVersion(event.getTaggedPriceVersion());
            newPriceBucket.setEntityStatus(event.getEntityStatus());
            newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));

            priceBucketViewRepository.save(newPriceBucket);
            ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
            //set next forecast date as current date + configured duration for repeating forecast. SHOULD IT BE THAT LONG???
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
            subscriptionCommandGateway.executeAsync(command);
        }
    }
}