package com.affaince.subscription.subscriber.command.event;

/**
 * Created by rbsavaliya on 06-09-2015.
 */
public class CouponCodeAddedEvent {

    private String subscriberId;
    private String couponCode;

    public CouponCodeAddedEvent(String subscriberId, String couponCode) {
        this.subscriberId = subscriberId;
        this.couponCode = couponCode;
    }

    public CouponCodeAddedEvent() {
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
