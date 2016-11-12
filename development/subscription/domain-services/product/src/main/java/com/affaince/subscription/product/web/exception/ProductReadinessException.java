package com.affaince.subscription.product.web.exception;

import com.affaince.subscription.common.type.ProductStatus;

/**
 * Created by anayonkar on 13/2/16.
 */
public class ProductReadinessException extends Exception {

    private static final String message = "Product %s is not ready for %s";

    public ProductReadinessException(String message) {
        super(message);
    }

    public static ProductReadinessException build(String productId,
                                                  ProductStatus status) {
        return new ProductReadinessException(String.format(message, productId, status));
    }

}
