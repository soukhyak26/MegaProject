package com.affaince.subscription.payments.exception;

public class MultipleActiveTaggedPricesException extends RuntimeException {
    private static final String message = "For product %s,multiple tagged prices are active at the same time" ;

    public MultipleActiveTaggedPricesException(String message) {
        super(message);
    }

    public static MultipleActiveTaggedPricesException build(String productId){
        return new MultipleActiveTaggedPricesException(String.format(message, productId));
    }

}
