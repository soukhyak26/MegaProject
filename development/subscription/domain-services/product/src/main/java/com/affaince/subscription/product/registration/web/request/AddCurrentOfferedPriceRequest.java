package com.affaince.subscription.product.registration.web.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

/**
 * Created by rbsavaliya on 07-08-2015.
 */
public class AddCurrentOfferedPriceRequest {

    @DecimalMin(value = "0.1")
    @Digits(integer = 6, fraction = 2)
    private double currentOfferedPrice;

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }
}
