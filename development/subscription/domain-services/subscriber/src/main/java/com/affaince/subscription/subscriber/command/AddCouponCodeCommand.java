package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class AddCouponCodeCommand {
    @TargetAggregateIdentifier
    private String subscriberId;
    private String couponCode;

    public AddCouponCodeCommand(String subscriberId, String couponCode) {
        this.subscriberId = subscriberId;
        this.couponCode = couponCode;
    }

    public AddCouponCodeCommand() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
