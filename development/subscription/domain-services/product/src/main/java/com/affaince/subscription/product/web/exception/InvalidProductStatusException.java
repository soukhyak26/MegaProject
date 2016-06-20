package com.affaince.subscription.product.web.exception;

import com.affaince.subscription.common.type.ProductStatus;

/**
 * Created by anayonkar on 13/2/16.
 */
public class InvalidProductStatusException extends Exception {

    private static final String message = "For product %s, while changing status to %s, " +
            "status %s not found in status list";

    public InvalidProductStatusException(String message) {
        super(message);
    }

    public static InvalidProductStatusException build(String productId,
                                                      ProductStatus actualStatus,
                                                      ProductStatus expectedStatus) {
        return new InvalidProductStatusException(String.format(message, productId, actualStatus, expectedStatus));
    }

}
