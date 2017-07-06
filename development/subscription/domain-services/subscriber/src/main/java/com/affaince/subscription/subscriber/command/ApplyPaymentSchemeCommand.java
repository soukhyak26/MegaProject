package com.affaince.subscription.subscriber.command;

/**
 * Created by mandar on 7/6/2017.
 */
public class ApplyPaymentSchemeCommand {
    private String subscriberId;
    private String paymentSchemeId;
    public ApplyPaymentSchemeCommand(String subscriberId, String paymentSchemeId) {
        this.subscriberId=subscriberId;
        this.paymentSchemeId=paymentSchemeId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }
}
