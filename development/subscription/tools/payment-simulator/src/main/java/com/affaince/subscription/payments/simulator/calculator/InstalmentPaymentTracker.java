package com.affaince.subscription.payments.simulator.calculator;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.vo.DeliveryDispatchApprovalResult;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by mandar on 7/6/2017.
 */
//deliveries "before" which payment is expected.
//amount of payment expected
//amount of payment fulfilled
public class InstalmentPaymentTracker implements Comparable<InstalmentPaymentTracker>{
    private String deliveryId;
    private int deliverySequence;
    private Set<DeliveryPaymentTracker> deliverySequencesManagedByATracker;
    private double paymentExpected;
    private double paymentReceived;
    private DeliveryStatus deliveryStatus;
    private boolean isValid;

    public InstalmentPaymentTracker(int deliverySequence) {
        this.deliverySequence = deliverySequence;
        this. isValid=true;
        this.deliverySequencesManagedByATracker= new TreeSet<>();
        this.deliveryStatus= DeliveryStatus.CREATED;
    }

    public InstalmentPaymentTracker() {
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public double getPaymentExpected() {
        return paymentExpected;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public void addToPaymentExpected(double amount) {
        this.paymentExpected += amount;
    }

    public void deductFromPaymentExpected(double amount) {
        this.paymentExpected -= amount;
    }

    public void addToPaymentReceived(double amount) {
        this.paymentReceived += amount;
        this.paymentExpected -= amount;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public void setDeliverySequence(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }

    public Set<DeliveryPaymentTracker> getDeliverySequencesManagedByATracker() {
        return deliverySequencesManagedByATracker;
    }

    public void setDeliverySequencesManagedByATracker(Set<DeliveryPaymentTracker> deliverySequencesManagedByATracker) {
        this.deliverySequencesManagedByATracker = deliverySequencesManagedByATracker;
    }
    public void addToDeliverySequencesManagedByTracker(int deliverySequence){
        DeliveryPaymentTracker deliveryPaymentTracker= new DeliveryPaymentTracker(deliverySequence);
        if(!deliverySequencesManagedByATracker.contains(deliveryPaymentTracker)){
            deliverySequencesManagedByATracker.add(deliveryPaymentTracker);
        }
    }
    public void removeFromDeliverySequencesManagedByTracker(int deliverySequence){
        DeliveryPaymentTracker deliveryPaymentTracker= new DeliveryPaymentTracker(deliverySequence);
        if(!deliverySequencesManagedByATracker.contains(deliveryPaymentTracker)){
            deliverySequencesManagedByATracker.remove(deliveryPaymentTracker);
        }
    }
    public void setPaymentExpected(double paymentExpected) {
        this.paymentExpected = paymentExpected;
    }

    public void setPaymentReceived(double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void deductFromPaymentReceived(double amount) {
        this.paymentReceived -= amount;
    }

    @JsonIgnore
    public boolean isDeliveryDueAmountFulfilled() {
        return (paymentExpected == paymentReceived);
    }

    @JsonIgnore
    public boolean isGivenDeliverySequenceManagedByTracker(int deliverySequence) {
        return deliverySequencesManagedByATracker.stream().anyMatch(dsmt-> dsmt.getDeliverySequence() == deliverySequence);
    }

    public DeliveryPaymentTracker findDeliveryTrackerByDeliverySequence(int deliverySequence){
            return deliverySequencesManagedByATracker.stream().filter(dsmt->dsmt.getDeliverySequence()==deliverySequence).collect(Collectors.toList()).get(0);
    }

    public DeliveryDispatchApprovalResult isDeliveryApproved(int deliverySequence){
        //the delivery sequence where payment is expected
        if (deliverySequence == this.getDeliverySequence()) {
            return new DeliveryDispatchApprovalResult(deliverySequence, this.getPaymentExpected() == this.getPaymentReceived(),this.getPaymentExpected()-this.getPaymentReceived());
        }else if(deliverySequence != this.getDeliverySequence() && isGivenDeliverySequenceManagedByTracker(deliverySequence)){
            //here no payment is expected as the delivery sequence is before the sequence where payment is expected
            return new DeliveryDispatchApprovalResult(deliverySequence,true,this.getPaymentExpected()-this.getPaymentReceived());
        }else{
            return new DeliveryDispatchApprovalResult(deliverySequence,false,this.getPaymentExpected()-this.getPaymentReceived());
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstalmentPaymentTracker that = (InstalmentPaymentTracker) o;

        return deliverySequence == that.deliverySequence;

    }

    @Override
    public int hashCode() {
        return deliverySequence;
    }

    public void correctDeliveryPaymentTracking(ModifiedDeliveryContent modifiedDeliveryContent) {
        int sequecne = modifiedDeliveryContent.getSequence();
        DeliveryPaymentTracker deliveryPaymentTracker = this.findDeliveryTrackerByDeliverySequence(sequecne);
        double remainingDueAsPerTracker=(deliveryPaymentTracker.getPaymentExpected()-deliveryPaymentTracker.getPaymentReceived());
        if(modifiedDeliveryContent.getCorrectedRemainingDuePayment() != remainingDueAsPerTracker){
            deliveryPaymentTracker.setPaymentExpected(deliveryPaymentTracker.getPaymentExpected()+(modifiedDeliveryContent.getCorrectedRemainingDuePayment()-remainingDueAsPerTracker));
        }
    }

    @Override
    public int compareTo(InstalmentPaymentTracker o) {
        return this.getDeliverySequence() - o.getDeliverySequence();
    }
}
