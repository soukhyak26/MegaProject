package com.affaince.subscription.product.query.exception;

/**
 * Created by rbsavaliya on 12-11-2016.
 */
public class PriceInitializationNotAllowedException extends Exception {
    private static final String message = "For product %s, price initialization is not allowed, " +
            "as it already has existing price";


    public PriceInitializationNotAllowedException(String s) {
        super(message);
    }

    public static PriceInitializationNotAllowedException build(String productId) {
        return new PriceInitializationNotAllowedException(String.format(message, productId));
    }

}
