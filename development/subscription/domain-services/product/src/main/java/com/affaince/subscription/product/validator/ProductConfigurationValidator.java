package com.affaince.subscription.product.validator;

import com.affaince.subscription.common.type.ProductReadinessStatus;
import com.affaince.subscription.common.type.ProductStatus;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by anayonkar on 28/2/16.
 */
public final class ProductConfigurationValidator {
    public static Set<ProductReadinessStatus> getProductReadinessStatus(List<ProductStatus> productActivationStatusList) {
        final Set<ProductReadinessStatus> productReadinessStatuses = new HashSet<>();
        ProductStatus nextProductStatus = null;
        if (!productActivationStatusList.contains(ProductStatus.PRODUCT_DEACTIVATED)) {
            for (ProductStatus productStatus : productActivationStatusList) {
                switch (productStatus) {
                    case SUBSCRIPTION_RULES_CONFIGURED:
                        productReadinessStatuses.add(ProductReadinessStatus.REGISTERABLE);
                    case PRODUCT_REGISTERED:
                        productReadinessStatuses.add(ProductReadinessStatus.CONFIGURABLE);
                        productReadinessStatuses.add(ProductReadinessStatus.SUBSCRIPTION_RULES_CONFIGURABLE);
                        break;
                    case PRODUCT_CONFIGURED:
                            productReadinessStatuses.add(ProductReadinessStatus.FORECASTABLE);
                        productReadinessStatuses.add(ProductReadinessStatus.STEPFORECASTABLE);
                        break;
                    case PRODUCT_FORECASTED:
                        productReadinessStatuses.add(ProductReadinessStatus.STEPFORECASTABLE);
                        //TODO: It should only be added after BUSINESS_PROVISION_CONFIGURABLE. Need to change after implementation of Business Provisioning
                        productReadinessStatuses.add(ProductReadinessStatus.PRICEASSIGNABLE);
                        productReadinessStatuses.add(ProductReadinessStatus.BUSINESS_PROVISION_CONFIGURABLE);
                        break;
                    case PRODUCT_STEPFORECAST_CREATED:
                        productReadinessStatuses.add(ProductReadinessStatus.BUSINESS_PROVISION_CONFIGURABLE);
                        //TODO: It should only be added after BUSINESS_PROVISION_CONFIGURABLE. Need to change after implementation of Business Provisioning
                        productReadinessStatuses.add(ProductReadinessStatus.PRICEASSIGNABLE);
                        break;
                    case BUSINESS_PROVISIONED:
                        productReadinessStatuses.add(ProductReadinessStatus.PRICEASSIGNABLE);
                        break;
                    case PRODUCT_PRICE_ASSIGNED:
                        productReadinessStatuses.add(ProductReadinessStatus.ACTIVABLE);
                        break;
                }
            }
            return productReadinessStatuses;
        }
        return null;
    }
}
