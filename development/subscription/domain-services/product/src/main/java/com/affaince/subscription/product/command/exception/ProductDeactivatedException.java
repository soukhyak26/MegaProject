package com.affaince.subscription.product.command.exception;

import com.affaince.subscription.common.type.ProductStatus;

/**
 * Created by mandar on 16-10-2016.
 */
public class ProductDeactivatedException extends Exception {
    private static final String message = "For product %s,current sttus is %s, ";

    public ProductDeactivatedException(String message) {
        super(message);
    }

    public static ProductDeactivatedException build(String productId,
                                                    ProductStatus actualStatus) {
        return new ProductDeactivatedException(String.format(message, productId, actualStatus));
    }
}
