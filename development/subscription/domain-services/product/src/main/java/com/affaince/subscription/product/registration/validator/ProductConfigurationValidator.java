package com.affaince.subscription.product.registration.validator;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.product.registration.query.view.ProductStatusView;
import com.affaince.subscription.product.registration.web.exception.InvalidProductStatusException;

import java.util.List;

/**
 * Created by anayonkar on 28/2/16.
 */
public final class ProductConfigurationValidator {
    public static boolean validateProductConfiguration(ProductStatusView productStatusView) throws InvalidProductStatusException {
        List<ProductStatus> statusList = productStatusView.getProductStatuses();
        boolean registered = false;
        boolean configured = false;
        boolean forecasted = false;
        //boolean completed = false;
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
                        throw InvalidProductStatusException.build(productStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                case PRODUCT_FORECASTED:
                    if(registered) {
                        forecasted = true;
                    } else {
                        throw InvalidProductStatusException.build(productStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                /*case PRODUCT_COMPLETED:
                    if(configured && forecasted) {
                        completed = true;
                    } else {
                        throw InvalidProductStatusException.build(productStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;*/
                case PRODUCT_EXPENSES_DISTRIBUTED:
                    if(configured && forecasted) {
                        expenseDistributed = true;
                    } else {
                        throw InvalidProductStatusException.build(productStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
                case PRODUCT_ACTIVATED:
                    if(expenseDistributed) {
                        activated = true;
                    } else {
                        throw InvalidProductStatusException.build(productStatusView.getProductId(),
                                productStatus,
                                ProductStatus.PRODUCT_REGISTERED);
                    }
                    break;
            }
        }
        return true;
    }

}
