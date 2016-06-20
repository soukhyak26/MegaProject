package com.affaince.subscription.product.validator;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.query.view.ProductActivationStatusView;
import com.affaince.subscription.product.web.exception.InvalidProductStatusException;

import java.util.List;

/**
 * Created by anayonkar on 28/2/16.
 */
public final class ProductConfigurationValidator {
    public static boolean validateProductConfiguration(ProductActivationStatusView productActivationStatusView) throws InvalidProductStatusException {
        List<ProductStatus> statusList = productActivationStatusView.getProductStatuses();
        boolean registered = false;
        boolean configured = false;
        boolean forecasted = false;
        boolean expenseDistributed = false;
        boolean activated = false;
        ProductStatus nextProductStatus = null;
        for(ProductStatus productStatus : statusList) {
            switch (productStatus) {
                case PRODUCT_REGISTERED:
                    registered = true;
                    break;
                case PRODUCT_CONFIGURED:
                    if(registered) {
                        configured = true;
                    } else {
                        throw InvalidProductStatusException.build(productActivationStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                case PRODUCT_FORECASTED:
                    if(registered) {
                        forecasted = true;
                    } else {
                        throw InvalidProductStatusException.build(productActivationStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                case PRODUCT_EXPENSES_DISTRIBUTED:
                    if(configured && forecasted) {
                        expenseDistributed = true;
                    } else {
                        throw InvalidProductStatusException.build(productActivationStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                case PRODUCT_ACTIVATED:
                    if(expenseDistributed) {
                        activated = true;
                    } else {
                        throw InvalidProductStatusException.build(productActivationStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
            }
        }
        return true;
    }

}
