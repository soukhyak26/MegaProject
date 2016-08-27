package com.affaince.subscription.product.services.pricing.exception;

/**
 * Created by mandark on 28-02-2016.
 */
public class InaccurateRegressionException extends RuntimeException {
    private String message;

    private InaccurateRegressionException(String message) {
        this.message = message;
    }

    public InaccurateRegressionException() {
        new InaccurateRegressionException(String.format("Regression paramters are not reliable enough for usage"));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
