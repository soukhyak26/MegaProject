package com.affaince.subscription.product.validator;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.command.exception.ProductDeactivatedException;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;

import java.util.List;

/**
 * Created by anayonkar on 28/2/16.
 */
public final class ProductConfigurationValidator {
    public static boolean isProductReadyForActivation(String productId, List<ProductStatus> productActivationStatusList) throws InvalidProductStatusException, ProductDeactivatedException {
        boolean registered = false;
        boolean configured = false;
        boolean forecasted = false;
        boolean stepForecasted = false;
        boolean priceAssigned = false;
        boolean activated = false;
        ProductStatus nextProductStatus = null;
        if (!productActivationStatusList.contains(ProductStatus.PRODUCT_DEACTIVATED)) {
            for (ProductStatus productStatus : productActivationStatusList) {
                switch (productStatus) {
                    case PRODUCT_REGISTERED:
                        registered = true;
                        break;
                    case PRODUCT_CONFIGURED:
                        if (registered) {
                            configured = true;
                        } else {
                            throw InvalidProductStatusException.build(productId,
                                    productStatus,
                                    ProductStatus.PRODUCT_REGISTERED);
                        }
                        break;
                    case PRODUCT_FORECASTED:
                        if (registered) {
                            forecasted = true;
                        } else {
                            throw InvalidProductStatusException.build(productId,
                                    productStatus,
                                    ProductStatus.PRODUCT_REGISTERED);
                        }
                        break;
                    case PRODUCT_STEPFORECAST_CREATED:
                        if (registered) {
                            stepForecasted = true;
                        } else {
                            throw InvalidProductStatusException.build(productId,
                                    productStatus,
                                    ProductStatus.PRODUCT_REGISTERED);
                        }
                        break;

                    case PRODUCT_PRICE_ASSIGNED:
                        if (registered) {
                            priceAssigned = true;
                        } else {
                            throw InvalidProductStatusException.build(productId,
                                    productStatus,
                                    ProductStatus.PRODUCT_REGISTERED);
                        }
                        break;

                    case PRODUCT_ACTIVATED:
                        if (registered && configured && forecasted && stepForecasted && priceAssigned) {
                            activated = true;
                        } else {
                            throw InvalidProductStatusException.build(productId,
                                    productStatus,
                                    ProductStatus.PRODUCT_REGISTERED);
                        }
                        break;
                }
            }
            return activated;
        } else {
            throw ProductDeactivatedException.build(productId, ProductStatus.PRODUCT_DEACTIVATED);
        }
    }

}
