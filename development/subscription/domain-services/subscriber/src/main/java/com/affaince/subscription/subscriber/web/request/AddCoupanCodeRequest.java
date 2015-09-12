package com.affaince.subscription.subscriber.web.request;

import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class AddCoupanCodeRequest {

    @NotNull
    private String couponCode;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
