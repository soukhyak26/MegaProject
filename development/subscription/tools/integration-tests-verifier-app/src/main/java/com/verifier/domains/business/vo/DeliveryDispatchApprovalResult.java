package com.verifier.domains.business.vo;

/**
 * Created by mandar on 2/18/2018.
 */
public class DeliveryDispatchApprovalResult {
    private int deliverySequence;
    private boolean isDispatchApproved;
    private boolean duePayment;

    public DeliveryDispatchApprovalResult(int deliverySequence, boolean isDispatchApproved, boolean duePayment) {
        this.deliverySequence = deliverySequence;
        this.isDispatchApproved = isDispatchApproved;
        this.duePayment = duePayment;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public boolean isDispatchApproved() {
        return isDispatchApproved;
    }

    public boolean isDuePayment() {
        return duePayment;
    }
}
