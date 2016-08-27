package com.affaince.subscription.product.services.pricing.exception;

/**
 * Created by mandark on 28-02-2016.
 */
public class PricingEligibilityViolationException extends RuntimeException {
    private String message;

    private PricingEligibilityViolationException(String message) {
        this.message = message;
    }

    public PricingEligibilityViolationException() {
        new PricingEligibilityViolationException(String.format("Product is not eligible for pricing calculation"));
    }

    @Override
    public String getMessage() {
        return message;
    }

}
