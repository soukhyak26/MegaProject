package com.affaince.subscription.payments.command.event;

/**
 * Created by mandar on 7/11/2017.
 */
public class DeliveryDispatchApprovalSentEvent {
    private String subscriberId;
    private String subscriptionId;
    private String deliveryId;
    private boolean validateForDispatchFlag;
    public DeliveryDispatchApprovalSentEvent(String subscriberId,String subscriptionId, String deliveryId, boolean validateForDispatchFlag) {
        this.subscriberId=subscriberId;
        this.deliveryId=deliveryId;
        this.subscriptionId=subscriptionId;
        this.validateForDispatchFlag=validateForDispatchFlag;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public boolean isValidateForDispatchFlag() {
        return validateForDispatchFlag;
    }
}
