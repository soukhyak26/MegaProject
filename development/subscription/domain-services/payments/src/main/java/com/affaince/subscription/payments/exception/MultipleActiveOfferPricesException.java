package com.affaince.subscription.payments.exception;

public class MultipleActiveOfferPricesException extends RuntimeException {
    private static final String message = "For product %s,multiple offer prices are active at the same time" ;

    public MultipleActiveOfferPricesException(String message) {
        super(message);
    }

    public static MultipleActiveOfferPricesException build(String productId){
        return new MultipleActiveOfferPricesException(String.format(message, productId));
    }

}
