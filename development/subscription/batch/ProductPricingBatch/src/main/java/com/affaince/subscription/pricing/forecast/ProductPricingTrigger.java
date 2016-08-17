package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductPseudoActualsViewRepository;
import com.affaince.subscription.pricing.query.view.ProductPseudoActualsView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 16-08-2016.
 */
public class ProductPricingTrigger {
    @Autowired
    private ProductPseudoActualsViewRepository productPseudoActualsViewRepository;

    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    public ProductDemandTrend triggerProductPricing(String productId, double interpolatedTotalSubscriptionsOnDay) {
        boolean doTriggerPrice = false;
        LocalDate currentDate = LocalDate.now();
        final ProductVersionId productVersionId = new ProductVersionId(productId, currentDate);
        final ProductPseudoActualsView productPseudoActualsView = productPseudoActualsViewRepository.findOne(productVersionId);
        // final double interpolatedTotalSubscriptionsOnDay = findInterpolatedTotalSubscriptionCountOnADay(productId, currentDate);
        final short changeThresholdForPriceChange =
                productConfigurationViewRepository.findOne(productId).getChangeThresholdForPriceChange();
        double difference = productPseudoActualsView.getTotalNumberOfExistingSubscriptions() - interpolatedTotalSubscriptionsOnDay;
        if (difference >= (interpolatedTotalSubscriptionsOnDay * changeThresholdForPriceChange) / 100) {
            return ProductDemandTrend.UPWARD;
        } else if (difference <= ((interpolatedTotalSubscriptionsOnDay * changeThresholdForPriceChange) / 100)) {
            return ProductDemandTrend.DOWNWARD;
        }
        return ProductDemandTrend.NOCHANGE;
    }

}
